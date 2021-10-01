package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;

import java.util.List;

public interface ScoreRepository {

    //점수 저장 기능
    void save(Score score);

    //전체 점수 정보 조회기능
    List<Score> findAll();

    //개별 점수 정보 조회기능
    Score findOne(int stuNum);

    //점수 정보 삭제 기능
    void remove(int stuNum);

}
