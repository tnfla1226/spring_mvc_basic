package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void regTest() {
       Member member = new Member();
       member.setAccount("abab12");

       //원본 비밀번호
        String rawPw = "5678!";
        //비밀번호 인코딩
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //암호와된 비밀번호
        String encodePw = encoder.encode(rawPw);

        member.setPassword(encodePw);
       member.setName("박영희");
       member.setEmail("abab12@gmail.com");
       member.setAuth(Auth.COMMON);

       memberMapper.register(member);
    }

    @Test
    @DisplayName("아이디, 이메일 중복확인에 성공해야 한다.")
    void duplicateTest() {
        String inputAccount = "abc4321";

        Map<String, Object> datas = new HashMap<>();
        datas.put("type", "account");
        datas.put("keyword", inputAccount);

        int result = memberMapper.isDuplicate(datas);

        assertTrue(result == 1);
    }

    @Test
    @DisplayName("로그인 검증을 수행해야 한다.")
    void loginTest() {
        //로그인 시도 아이디
        String inputId = "abab12";
        //로그인 시도 패스워드
        String inputPw = "5678!";

        //로그인 시도 아이디를 기반으로 회원정보를 조회
        Member member = memberMapper.getUser(inputId);

        if (member != null) {
            //db에 저장된 비번
            String dbPw = member.getPassword();

            //암호화된 비번을 디코딩해서 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공!");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }

        } else {
            System.out.println("회원가입을 먼저 진행하세요.");
        }
    }
}