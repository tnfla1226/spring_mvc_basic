package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //1. 게시물 목록 조회
    List<Board> getArticles();

    //2. 게시물 상세 조회
    Board getContent(int boardNo);

    //3. 게시물 등록
    boolean insertArticle(Board board);

    //4. 게시물 삭제
    boolean deleteArticle(int boardNo);

    // 5. 게시물 수정
    boolean modifyArticle(Board board);
}
