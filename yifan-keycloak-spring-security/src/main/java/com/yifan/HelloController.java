package com.yifan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/admin")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }
}