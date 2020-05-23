package com.ohayou.jwtserver.entity;

import lombok.Data;

/**
 * @author liyan
 * @date 2020/5/19 下午9:50
 */
@Data
public class SysRoleMenu {

    private Integer id;

    private Integer roleId;

    private Integer menuId;
}
