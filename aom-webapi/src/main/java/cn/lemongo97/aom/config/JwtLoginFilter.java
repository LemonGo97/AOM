package cn.lemongo97.aom.config;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.model.User;
import cn.lemongo97.aom.utils.GsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
        User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        return super.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer stringBuffer = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            stringBuffer.append(authority.getAuthority()).append(",");
        }
        String token = Jwts.builder()
                .claim("authorities", stringBuffer)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "sang@123")
                .compact();
        Map<String,Object> result = new HashMap<>();
        result.put("user",authResult.getPrincipal());
        result.put("token", token);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(GsonUtil.toJson(Result.success(result)));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result failure = Result.failure(ResultCode.USER_NOT_LOGGED_IN, exception.getMessage());
        if (exception instanceof LockedException) {
            failure.setMessage("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            failure.setMessage("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            failure.setMessage("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            failure.setMessage("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            failure.setMessage("用户名或者密码输入错误，请重新输入!");
        }
        out.write(GsonUtil.toJson(failure));
        out.flush();
        out.close();
    }
}