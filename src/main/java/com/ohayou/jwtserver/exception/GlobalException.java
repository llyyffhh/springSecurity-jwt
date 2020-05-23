package com.ohayou.jwtserver.exception;

import com.ohayou.jwtserver.response.ErrorMsg;

/**
 * @author liyan
 * @date 2020/5/20 下午10:29
 */
public class GlobalException extends Exception{

    private ErrorMsg errorMsg;

    public GlobalException(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
    }
}
