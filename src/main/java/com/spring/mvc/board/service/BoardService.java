package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    //final을 초기화해주는 생성자
public class BoardService {

    private final BoardMapper boardMapper;

    //게시물 목록 가져오기
    public List<Board> getArticles() {
        List<Board> articles = boardMapper.getArticles();

        return articles;
    }

    //게시글 상세조회
    public Board getContent(int boardNo) {
        Board content = boardMapper.getContent(boardNo);
        return content;
    }

    //게시글 등록
    public boolean insert(Board board) {
        return boardMapper.insertArticle(board);
    }

    //게시글 삭제
    public boolean remove(int boardNo) {
        return boardMapper.deleteArticle(boardNo);
    }

}
