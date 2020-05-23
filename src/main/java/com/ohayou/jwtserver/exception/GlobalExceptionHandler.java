package com.ohayou.jwtserver.exception;

import com.ohayou.jwtserver.response.ErrorMsg;
import com.ohayou.jwtserver.response.R;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liyan
 * @date 2020/5/20 下午10:29
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R globalExceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return R.error(exception.getErrorMsg());
        } else {
            e.printStackTrace();
            return R.error(ErrorMsg.SERVER_ERROR);
        }
    }

}
