package com.kjq.kjqinterface.controller;

import com.kjq.kjqinterface.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 名称API
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name){
        return "GET请求 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name){
        return "POST请求 你的名字是" + name;
    }

    @PostMapping("/json")
    public String getUserNameByPost(HttpServletResponse response, @RequestBody User user) throws InterruptedException {
//        Thread.currentThread().sleep(10000);
//        int i = 1 / 0;
        return "传递Body参数" + user.getName();
    }
}
