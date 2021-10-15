package com.spring.mvc.reply.controller;

import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/reply")
@Log4j2
@RequiredArgsConstructor
public class RelpyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청 처리
    @GetMapping("/{boardNo}")
    public ResponseEntity<List<Reply>> List(
            @PathVariable int boardNo) {
        log.info("/api/v1/reply/" + boardNo + " GET!!");
        List<Reply> replylist = replyService.getList(boardNo);

        return new ResponseEntity<>(replylist, HttpStatus.OK);
    }
}
