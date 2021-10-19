package com.spring.mvc.reply.service;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    //댓글 목록 조회
    public Map<String, Object> getList(int boardNo, Page page) {
        PageMaker maker
                = new PageMaker(page, getCount(boardNo));

        Map<String, Object> replyMap = new HashMap<>();
        replyMap.put("replyList", replyMapper.getList(boardNo, page));
        replyMap.put("maker", maker);

        return replyMap;
    }

    //댓글 총 개수 조회
    public int getCount(int boardNo) {
        return  replyMapper.getCount(boardNo);
    }

    //댓글 추가
    public boolean register(Reply reply) {
        return replyMapper.save(reply);
    }

    //댓글 수정
    public boolean modify(Reply reply) {
        return replyMapper.update(reply);
    }

    //댓글 삭제
    public boolean remove(int replyNo) {
        return replyMapper.delete(replyNo);
    }


    //댓글 개별 조회
    public Reply get(int replyNo) {
        return replyMapper.read(replyNo);
    }
}
