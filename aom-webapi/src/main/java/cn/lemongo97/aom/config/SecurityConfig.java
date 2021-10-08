package cn.lemongo97.aom.config;

import cn.hutool.core.map.MapUtil;
import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.model.User;
import cn.lemongo97.aom.service.UserService;
import cn.lemongo97.aom.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String REDIS_TOKEN_PREFIX = "string:aom-token:";
    private final static String HEADER_TOKEN_NAME = "X-Token";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/t_admin").hasRole("admin")
                .antMatchers("/t_user").hasRole("user")
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .addLogoutHandler((request, response, authentication) -> {

                })
                .and()
                .addFilterBefore(new AbstractAuthenticationProcessingFilter("/login", authenticationManager()) {
                    @Override
                    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException {
                        User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
                        return super.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                    }

                    @Override
                    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (GrantedAuthority authority : authResult.getAuthorities()) {
                            stringBuffer.append(authority.getAuthority()).append(",");
                        }
                        String token = Jwts.builder()
                                .claim("authorities", stringBuffer)
                                .setSubject(authResult.getName())
                                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                                .signWith(SignatureAlgorithm.HS512, "sang@123")
                                .compact();
                        redisTemplate.opsForValue().setIfAbsent(REDIS_TOKEN_PREFIX + token, token, 30, TimeUnit.MINUTES);
                        ResponseUtils.writeAndFlush(resp, Result.success(MapUtil
                                .builder()
                                .put("user", authResult.getPrincipal())
                                .put("token", token)
                                .build()));
                    }

                    @Override
                    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
                        Result failure;
                        if (exception instanceof LockedException) {
                            failure = Result.failure(ResultCode.USER_ACCOUNT_LOCKED, exception.getStackTrace());
                        } else if (exception instanceof CredentialsExpiredException) {
                            failure = Result.failure(ResultCode.USER_PASSWD_EXPIRED, exception.getStackTrace());
                        } else if (exception instanceof AccountExpiredException) {
                            failure = Result.failure(ResultCode.USER_ACCOUNT_EXPIRED, exception.getStackTrace());
                        } else if (exception instanceof DisabledException) {
                            failure = Result.failure(ResultCode.USER_ACCOUNT_FORBIDDEN, exception.getStackTrace());
                        } else if (exception instanceof BadCredentialsException) {
                            failure = Result.failure(ResultCode.USER_LOGIN_ERROR, exception.getStackTrace());
                        } else {
                            failure = Result.failure(ResultCode.USER_ACCOUNT_UNKNOWN_EXCEPTION, exception.getStackTrace());
                        }
                        ResponseUtils.writeAndFlush(response, failure);
                    }
                }, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new GenericFilterBean() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
                        try {
                            HttpServletRequest request = (HttpServletRequest) servletRequest;
                            String jwtToken = request.getHeader(HEADER_TOKEN_NAME);
                            Claims claims = Jwts.parser().setSigningKey("sang@123").parseClaimsJws(jwtToken.replace("Bearer", "")).getBody();
                            String username = claims.getSubject();
                            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                            SecurityContextHolder.getContext().setAuthentication(token);
                            filterChain.doFilter(request, servletResponse);
                        } catch (Exception e) {
                            if (e instanceof ExpiredJwtException) {
                                log.error(ResultCode.USER_TOKEN_EXPIRED.message(), e);
                                ResponseUtils.writeAndFlush(servletResponse, Result.failure(ResultCode.USER_TOKEN_EXPIRED));
                            } else {
                                log.error(ResultCode.UNKNOWN_EXCEPTION.message(), e);
                                ResponseUtils.writeAndFlush(servletResponse, Result.failure(ResultCode.UNKNOWN_EXCEPTION, "Token 验证失败！", Arrays.toString(e.getStackTrace())));
                            }
                        }
                    }
                }, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}