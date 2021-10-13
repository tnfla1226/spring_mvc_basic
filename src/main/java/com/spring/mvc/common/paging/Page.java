package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Page {

    private int pageNum; //페이지 번호
    private int amount; //한 페이지당 게시물 수
    private String type; //검색 조건
    private String keyword = ""; //검색어

    public Page() {
        this(1, 10);
    }

    public Page(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;   //음수가 입력되면 1페이지로 돌려라
            return;
        }
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        if (amount < 0 || amount > 100) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }
}
