package com.spring.mvc.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ApiControllerV2 {

    /*
        # ResponseEntity
        - 스프링 REST API가 응답할 때
        응답데이터뿐만 아니라 응답상태코드, 응답 헤더 등을
        조작해서 전송할 수 있게 해주는 객체
    */

    @GetMapping("/hello")
    public ResponseEntity<String> hello(String p) {
        if (p.equals("hi")) {
            return new ResponseEntity<String>("나도 안녕~", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("인사해줘", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
