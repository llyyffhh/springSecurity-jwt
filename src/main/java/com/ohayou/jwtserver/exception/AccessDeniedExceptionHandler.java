package com.ohayou.jwtserver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohayou.jwtserver.response.ErrorMsg;
import com.ohayou.jwtserver.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liyan
 * @date 2020/5/22 下午10:10
 */
@Component
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String s = objectMapper.writeValueAsString(R.error(ErrorMsg.ACCESS_DENIED));
        writer.write(s);
        writer.flush();
        writer.close();
    }
}
