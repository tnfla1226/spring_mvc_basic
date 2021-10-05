package com.spring.mvc.score.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter
@ToString
public class Score {

    private int stuNum; //학번
    private String name; //이름
    private  int kor; //국어점수
    private  int eng; //영어점수
    private  int math; //수학점수
    private  int total; //총점
    private  double average; //평균

    //순차적 학번 부여 정적필드드
   private static int seq;

    public Score() {
        this.stuNum = ++seq;
    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
    }

    public void calcTotal() {
        this.total = this.kor + this.eng + this.math;
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;
    }

    public Score(String name, int kor, int eng, int math) {
        this();
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        calcTotal();
    }
}
