package com.spring.mvc;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DBConnectTest {

    //DB접속정보 설정
    private String userId = "hr";
    private String userPw = "hr";
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";

    @Test
    void connectTest() {

        try {
            //1. 드라이버클래스 동적로딩
            Class.forName(driver);

            //2. 연결 정보 생성
            Connection conn = DriverManager.getConnection(dbUrl, userId, userPw);
            System.out.println("DB연결 성공!");
            
            String sql = "SELECT first_name FROM employees";
            //3. SQL 실행객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("first_name");
                System.out.println("name = " + name);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 클래스 찾을 수 없음");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL 에러!");
            e.printStackTrace();
        }
    }
}
