package com.ohayou.jwtserver.entity;

import lombok.Data;

/**
 * @author liyan
 * @date 2020/5/19 下午9:47
 */
@Data
public class SysUser {

    private Integer id;

    private String username;

    private String password;

    private int enabled;

    private int roleId;


}
