package com.ohayou.jwtserver.exception;

import com.ohayou.jwtserver.response.ErrorMsg;
import org.springframework.security.core.AuthenticationException;


/**
 * @author liyan
 * @date 2020/5/23 下午2:35
 */
public class JwtTokenException extends AuthenticationException {
    public JwtTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtTokenException() {
        super(null);
    }

    private ErrorMsg errorMsg;

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
    }
}
