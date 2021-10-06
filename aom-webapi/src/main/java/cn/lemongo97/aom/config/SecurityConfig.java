package cn.lemongo97.aom.config;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.model.User;
import cn.lemongo97.aom.service.UserService;
import cn.lemongo97.aom.utils.GsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(GsonUtil.toJson(Result.success("注销成功")));
                            out.flush();
                            out.close();
                        }
                )
                .permitAll().and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.setStatus(401);
                            PrintWriter out = resp.getWriter();
                            Result result;
                            if (authException instanceof InsufficientAuthenticationException) {
                                result = Result.failure(ResultCode.USER_NOT_LOGGED_IN);
                            } else {
                                result = Result.failure(ResultCode.UNKNOWN_EXCEPTION);
                            }
                            out.write(GsonUtil.toJson(result));
                            out.flush();
                            out.close();
                        }
                );

        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            User user = (User)authentication.getPrincipal();
            user.setPassword(null);
            PrintWriter out = response.getWriter();
            out.write(GsonUtil.toJson(Result.success(user)));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
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
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/login");
        return loginFilter;
    }
}