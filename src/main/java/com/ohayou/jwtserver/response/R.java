package com.ohayou.jwtserver.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyan
 * @date 2020/5/19 下午10:48
 */

@Data
public class R {

    private int code;

    private String msg;

    private Map<String,Object> data;

    private R() {

    }

    public static R ok() {
        R r = new R();
        r.setCode(200);
        r.setMsg("success");
        return r;
    }

    public static R ok(String message) {
        R r = new R();
        r.setCode(200);
        r.setMsg(message);
        return r;
    }

    public static R ok(String message, Object data) {
        R r = new R();
        r.setCode(200);
        r.setMsg(message);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",data);
        r.setData(map);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(200);
        r.setMsg("success");
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",data);
        r.setData(map);
        return r;
    }

    public static R error(ErrorMsg errorMsg) {
        R r = new R();
        r.code = errorMsg.getCode();
        r.msg = errorMsg.getMsg();
        return r;
    }

    public static R argumentError(String msg) {
        R r = new R();
        r.code = 400;
        r.msg = msg;
        return r;
    }


}
