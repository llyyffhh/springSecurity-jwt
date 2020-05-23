package com.ohayou.jwtserver.filter;
import com.ohayou.jwtserver.exception.JwtTokenException;
import com.ohayou.jwtserver.exception.MyAuthenticationEntryPoint;
import com.ohayou.jwtserver.jwt.JwtTokenUtil;
import com.ohayou.jwtserver.response.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liyan
 * @date 2020/5/23 下午1:50
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService myUserDetailsService;

    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(jwtTokenUtil.getHeader());
        //如过认证通过
        if (SecurityContextHolder.getContext().getAuthentication() != null || StringUtils.isEmpty(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        JwtTokenException jwtTokenException = new JwtTokenException();
        jwtTokenException.setErrorMsg(ErrorMsg.TOKEN_ERROR_OR_EXPIRED);

        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (StringUtils.isEmpty(username) || jwtTokenUtil.isTokenExpired(token)) {
            myAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, jwtTokenException);
            return;
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            myAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, jwtTokenException);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //刷新token
        httpServletResponse.setHeader(jwtTokenUtil.getHeader(),jwtTokenUtil.refreshToken(token));
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
