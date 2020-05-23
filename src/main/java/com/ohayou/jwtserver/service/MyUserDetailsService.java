package com.ohayou.jwtserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ohayou.jwtserver.entity.SysRole;
import com.ohayou.jwtserver.entity.SysUser;
import com.ohayou.jwtserver.mapper.MenuMapper;
import com.ohayou.jwtserver.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyan
 * @date 2020/5/19 下午10:20
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    /**
     * 从数据库获取用户详情
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser currentUser = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", s));
        if (currentUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        MyUserDetail myUserDetail = new MyUserDetail();
        BeanUtils.copyProperties(currentUser, myUserDetail);
        String role = "ROLE_" + userMapper.findRoleByUsername(s).getRoleName();
        List<String> authorities = menuMapper.findMenusByUsername(s).stream()
                .map(menu -> {
                    return menu.getUrl();
                }).collect(Collectors.toList());
        authorities.add(role);
        myUserDetail.setAuthorities(AuthorityUtils.createAuthorityList(String.join(",", authorities)));
        return myUserDetail;
    }
}
