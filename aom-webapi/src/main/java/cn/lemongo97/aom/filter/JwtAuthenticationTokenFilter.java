package cn.lemongo97.aom.filter;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.exception.AomBaseException;
import cn.lemongo97.aom.model.LoginUser;
import cn.lemongo97.aom.utils.JsonUtil;
import cn.lemongo97.aom.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-Token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.error("Token非法！", e);
            throw new RuntimeException("非法的Token", e);
        }

        String redisKey = "string:login:" + userId;
        String json = redisTemplate.boundValueOps(redisKey).get();
        if (!StringUtils.hasText(json)) {
            throw new AomBaseException(ResultCode.USER_NOT_LOGGED_IN);
        }
        LoginUser loginUser = JsonUtil.fromJson(json, LoginUser.class);
        // TODO 获取权限信息，封装到 Authentication 中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}