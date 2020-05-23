package com.ohayou.jwtserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohayou.jwtserver.jwt.JwtTokenUtil;
import com.ohayou.jwtserver.response.ErrorMsg;
import com.ohayou.jwtserver.response.R;
import com.ohayou.jwtserver.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author liyan
 * @date 2020/5/21 下午10:18
 */
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    //必须设置authenticationManager
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {

        super.setAuthenticationManager(authenticationManager);
    }

    public JwtAuthenticationFilter() {
        this.setFilterProcessesUrl("/doLogin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;
        try {
            InputStream inputStream = request.getInputStream();
            Map<String,String> map = objectMapper.readValue(inputStream, Map.class);
            username = map.get("username");
            password = map.get("password");
        } catch (IOException e) {
            e.printStackTrace();
            username = "";
            password = "";
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        this.setDetails(request, upToken);
        return super.getAuthenticationManager().authenticate(upToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MyUserDetail details = (MyUserDetail)authResult.getPrincipal();
        String s = jwtTokenUtil.generateToken(details);
        response.setHeader(jwtTokenUtil.getHeader(),s);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(R.ok("登录成功")));
        writer.flush();
        writer.close();
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if (failed instanceof UsernameNotFoundException || failed instanceof BadCredentialsException) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(R.error(ErrorMsg.USERNAME_OR_PASSWORD_ERROR)));
            writer.flush();
            writer.close();
        }
    }
}
