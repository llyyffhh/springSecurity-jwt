package com.ohayou.jwtserver.controller;

import com.ohayou.jwtserver.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyan
 * @date 2020/5/23 下午3:24
 */
@RestController
@RequestMapping("/work")
public class WorkController {

    @GetMapping("/add")
    public R add() {
        return R.ok("布置任务");
    }

    @GetMapping("/edit")
    public R edit() {
        return R.ok("修改任务");
    }

    @GetMapping("/do")
    public R doWork() {
        return R.ok("完成任务");
    }
}
