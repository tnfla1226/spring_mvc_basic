package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
* 서버측에서 클라이언트에게 각종 페이지 정보를 응답하기 위해
* 페이징 데이터(이전, 다음버튼 활성화 여부, 시작, 끝 페이지 번호정보 등)을 생성하는 클래스
* */
@Getter @Setter @ToString
public class PageMaker {

    private int beginPage; //시작페이지 번호
    private int endPage; //끝페이지 번호
    private boolean prev, next; //이전, 다음 활성화 여부

    private Page page; //현재 요청 페이지 정보
    private int totalCount; //전체 게시물 수

    //한 화면에 배치할 페이지 수
    private static final int PAGE_COUNT = 5;

    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;

        //끝 페이지 수 계산
        // 올림 (현재 페이지 번호 / 한 화면에 보여줄 페이지 수) * 한 화면에 보여줄 페이지 수
        this.endPage = (int) Math.ceil((double) page.getPageNum() / PAGE_COUNT) * PAGE_COUNT;

        //시작 페이지 수 계산
        this.beginPage = this.endPage - PAGE_COUNT + 1;

        /*
            - 페이지 마지막 구간 끝페이지 보정
            : 총 게시물 수가 284개이고 한 화면당 10페이지씩 배치하고 있다면
              마지막 구간은 21 ~ 30이 아닌 21 ~ 29가 되어야 함.
              따라서 마지막 구간에서 endPage 재보정이 필요함.

              - 보정 공식 : 올림값 (총 게시물 수 / 한 페이지당 보여줄 게시물 수)
        */

        int realEnd = (int) Math.ceil((double) totalCount / page.getAmount());

        //보정은 마지막 구간에서만 일어나야 함.
        if (realEnd <= endPage) {
            this.endPage = realEnd;
        }

        //이전 버튼 활성화 여부
        this.prev = this.beginPage > 1;

        //다음 버튼 활성화 여부
        this.next = this.endPage < realEnd;
    }
}
