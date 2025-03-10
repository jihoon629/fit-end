package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 스프링에서 자체적으로 웹 띄우는 거에요
public class asdf {

    @GetMapping("/chcekjwt")
    public String getMethodName() {
        return "main";
    }

}
