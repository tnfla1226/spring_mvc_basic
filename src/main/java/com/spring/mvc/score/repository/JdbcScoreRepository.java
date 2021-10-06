package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        String sql = "INSERT INTO score VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // SQL에 ?를 채워야 함
            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());

            //INSERT, UPDATE, DELETE문은 executeUpdate() 메서드 사용
            int resultNum = pstmt.executeUpdate();

            if (resultNum == 0) {
                log.info("데이터 삽입 실패!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Score> findAll() {
        List<Score> scoreList = new ArrayList<>();

        Connection conn = null;
        try {
            //1. 드라이버 로딩
            Class.forName(driver);

            //2. 연결정보 객체 생성
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

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
        } finally {
            //5. DB 자원 해제
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num=?";

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // SQL에 ?를 채워야 함
            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Score(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void remove(int stuNum) {
        String sql = "DELETE FROM score WHERE stu_num=?";

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // SQL에 ?를 채워야 함
            pstmt.setInt(1, stuNum);


            //INSERT, UPDATE, DELETE문은 executeUpdate() 메서드 사용
            int resultNum = pstmt.executeUpdate();

            if (resultNum == 0) {
                log.info("데이터 삭제 실패!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
