package com.spring.mvc.v2;

import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String userId;
    private String userPw;
    private String userName;
    private int userAge;
    private List<String> hobbies;
}
