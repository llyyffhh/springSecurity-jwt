package com.ohayou.jwtserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ohayou.jwtserver.entity.SysRole;
import com.ohayou.jwtserver.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author liyan
 * @date 2020/5/19 下午9:52
 */

public interface UserMapper extends BaseMapper<SysUser> {

    @Select(
            "select r.* from sys_user u,sys_role r where u.username = #{username}" +
                    "and u.role_id = r.id"
    )
    SysRole findRoleByUsername(@Param("username") String username);
}
