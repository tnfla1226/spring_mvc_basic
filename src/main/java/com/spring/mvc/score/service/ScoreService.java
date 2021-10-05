package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//컨트롤러와 저장소 중간에서 데이터 중간처리를 담당하는 클래스
@Log4j2
@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(
            @Qualifier("jr") ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    //점수 정보 저장시에 해야할 처리
    public void register(Score score) {
        score.calcTotal();
        scoreRepository.save(score);
    }

}
