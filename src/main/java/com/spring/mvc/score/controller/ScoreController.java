package com.spring.mvc.score.controller;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreRepository;
import com.spring.mvc.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Log4j2 //로그 출력을 도와주는 기능
@RequiredArgsConstructor //final필드를 초기화하는 생성자 자동생성
public class ScoreController {

    private final ScoreRepository scoreRepository;
    private final ScoreService scoreService;

    /*@Autowired  //생성자에게 주입해라
    public ScoreController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }*/

    //점수프로그램 화면요청
    @GetMapping("score/list")
    public String scoreList(Model model) {
        List<Score> scores = scoreRepository.findAll();
        model.addAttribute("scoreList", scores);
        return "score/score-list";
    }

    //점수정보 저장 요청
    @PostMapping("/score/register")
    public String register(Score score) {   //이렇게 하면 score에서는 기본생성자+setter가 작동됨
        log.info("점수 등록 요청! - " + score);
        scoreService.register(score);
        //리다이렉트는 재요청 기능입니다.
        return "redirect:/score/list";
    }

    //점수정보 삭제 요청처리
    @GetMapping("/score/delete")
    public String delete(int stuNum) {
        log.info("점수 삭제 요청! - ");
        scoreRepository.remove(stuNum);
        return "redirect:/score/list";
    }

}
