package com.yijiahe.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weifw
 * @date 2024/9/6
 */
@Tag(name = "HelloController", description = "Hello接口")
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello,hresh";
    }

    @GetMapping("/hresh")
    public String sayHello() {
        return "hello,world";
    }


}
