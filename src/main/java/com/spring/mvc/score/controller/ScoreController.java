package com.spring.mvc.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScoreController {

    //점수프로그램 화면요청
    @GetMapping("score/list")
    public String scoreList() {
        return "score/score-list";
    }
}
