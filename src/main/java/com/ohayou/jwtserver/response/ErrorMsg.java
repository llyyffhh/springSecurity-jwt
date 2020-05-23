package com.ohayou.jwtserver.response;

/**
 * @author liyan
 * @date 2020/5/19 下午10:57
 */
public enum  ErrorMsg {
    SERVER_ERROR(500,"服务器内部错误"),
    ACCESS_DENIED(403,"没有访问该资源的权限"),
    USERNAME_OR_PASSWORD_ERROR(402,"用户名或密码有误"),
    UN_AUTHENTICATION(401,"您还未登录，请登录"),
    TOKEN_ERROR_OR_EXPIRED(406,"无效的token令牌或已过期");



    ErrorMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
