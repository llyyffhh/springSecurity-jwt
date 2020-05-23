package com.ohayou.jwtserver.controller;

import com.ohayou.jwtserver.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyan
 * @date 2020/5/23 下午3:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/add")
    public R add() {
        return R.ok("添加用户成功");
    }
    @GetMapping("/list")
    public R list() {
        return R.ok("查询用户成功");
    }
    @GetMapping("/edit")
    public R edit() {
        return R.ok("修改用户成功");
    }
    @GetMapping("/delete")
    public R delete() {
        return R.ok("删除用户成功");
    }
}
