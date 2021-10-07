package cn.lemongo97.aom.config;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.utils.ResponseUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            String jwtToken = request.getHeader("X-Token");
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
}