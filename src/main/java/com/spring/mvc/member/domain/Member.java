package com.spring.mvc.member.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String account; //계정명
    private String password; //비밀번호
    private String name; //사용자이름
    private String email; //이메일
    private Auth auth = Auth.ADMIN; //권한
    private Date regDate; //가입일자

}