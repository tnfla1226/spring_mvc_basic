package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jr")
@Log4j2
public class JdbcScoreRepository implements ScoreRepository {

    //DB접속정보 설정
    private String userId = "spring3";
    private String userPw = "1234";
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";

    @Override
    public void save(Score score) {

    }

    @Override
    public List<Score> findAll() {
        List<Score> scoreList = new ArrayList<>();

        try {
            //1. 드라이버 로딩
            Class.forName(driver);

            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(dbUrl, userId, userPw);

            //3. SQL실행 객체 생성
            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. SQL실행
            ResultSet rs = pstmt.executeQuery();

//            rs.next();
//            rs.next();
//            boolean next3 = rs.next();
//            boolean next4 = rs.next();
//            log.info("next4?" + next4);

            while (rs.next()) {

                Score score = new Score(rs);
                scoreList.add(score);
            }

//            log.info("이름: " + name);
//            log.info("총점: " + total);
//            log.info("next3?" + next3);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {
        return null;
    }

    @Override
    public void remove(int stuNum) {

    }
}
