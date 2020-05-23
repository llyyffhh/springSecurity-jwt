package com.ohayou.jwtserver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohayou.jwtserver.response.ErrorMsg;
import com.ohayou.jwtserver.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liyan
 * @date 2020/5/22 下午10:20
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String result;
        if (e instanceof JwtTokenException) {
            JwtTokenException jwtTokenException = (JwtTokenException)e;
            result = objectMapper.writeValueAsString(R.error(jwtTokenException.getErrorMsg()));
        } else {
            result = objectMapper.writeValueAsString(R.error(ErrorMsg.UN_AUTHENTICATION));
        }
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
