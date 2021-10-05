package com.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//DB관련 설정을 등록하는 클래스
@Configuration
@ComponentScan(basePackages = "com.spring.mvc")
public class DatabaseConfig {


    //DB연결정보, 커넥션풀 설정
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername("spring3");
        config.setPassword("1234");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        return new HikariDataSource(config);
    }
}
