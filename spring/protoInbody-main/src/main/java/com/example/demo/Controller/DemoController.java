package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/protected-endpoint")
    public String protectedEndpoint() {
        return "이 데이터는 보호된 엔드포인트에서 가져왔습니다.";
    }
}