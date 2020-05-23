package com.ohayou.jwtserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ohayou.jwtserver.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liyan
 * @date 2020/5/19 下午9:52
 */
public interface MenuMapper extends BaseMapper<SysMenu> {


    @Select("select m.* from sys_user u , sys_role r, sys_menu m, sys_role_menu rm where u.username = #{username} and u.role_id = r.id and rm.role_id = r.id and rm.menu_id = m.id")
    List<SysMenu> findMenusByUsername(@Param("username") String username);
}
