package com.spring.mvc.board.controller;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import com.spring.mvc.score.domain.Score;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시물 목록 요청
    @GetMapping("/list")
    public String list(Page page, Model model) {
        log.info("/board/list GET 요청 발생");
        List<Board> articles = boardService.getArticles(page);
        model.addAttribute("articles", articles);
        model.addAttribute("maker", new PageMaker(page, boardService.getCount()));

        return "board/list";
    }

    //게시물 상세 조회 요청
    @GetMapping("/content")
    public String content(int boardNo, Model model) {
        log.info("/board/content GET 요청! - 글번호:" + boardNo);
        Board content = boardService.getContent(boardNo);
        model.addAttribute("article", content);
        return "board/content";
    }

    //게시글 등록
    @GetMapping("/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/write")
    public String write(Board board, RedirectAttributes ra) {
        log.info("/board/write POST 요청! - " + board);
        if (boardService.insert(board)) {
            ra.addFlashAttribute("msg", "success");
        } else {
            ra.addFlashAttribute("msg", "fail");
        }
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(int boardNo) {
        log.info("/board/delete GET ! - " + boardNo);
        boardService.remove(boardNo);
        return "redirect:/board/list";
    }

    //글 수정 화면 요청처리
    @GetMapping("/modify")
    public String modify(int boardNo, Model model) {
        log.info("/board/modify GET! - " + boardNo);
        Board content = boardService.getContent(boardNo);
        model.addAttribute("article", content);
        return "board/modify";
    }

    //글 수정 완료처리 요청
    @PostMapping("/modify")
    public String modify(Board board) {
        log.info("/board/modify POST - " + board);
        boardService.modify(board);
        return "redirect:/board/content?boardNo=" + board.getBoardNo();
    }

}
