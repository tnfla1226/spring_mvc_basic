package com.spring.mvc.v2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

/*
* 스프링 MVC 프레임워크의 컨트롤러는 웹 브라우저의
* 요청을 받아 처리하며 응답할 화면(View)을 선택하는 역할을 합니다.
*
* 스프링이 이 클래스를 컨트롤러로 인식하게 하려면
* @Controller 아노테이션을 붙여야 합니다.
* */
@Controller
public class BasicController {

    //요청 처리 메서드

    //해당 경로로 URL요청이 오면 이 메서드가 처리하겠다!
    @GetMapping("/basic/test")
    public String test() {
        System.out.println("test요청이 들어왔어요!!");

        //리턴값은 내가 이 요청이 끝난 후 응답할 페이지의 경로
        return "check";
    }

    //요청 URL:   /basic
    //basic,.jsp가 열리도록 요청 메서드를 작성하세요.
    @GetMapping("/basic")
    public String basic() {
        System.out.println("test요청이 들어왔어요!!");

        //리턴값은 내가 이 요청이 끝난 후 응답할 페이지의 경로
        return "basic";
    }

    //요청 파라미터(요청시에 브라우저에서 넘어오는 데이터) 받기
    @GetMapping("/basic/join")
    public String join(HttpServletRequest request) {
        System.out.println("/basic/join 요청이 들어옴!");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        int userAge = Integer.parseInt(request.getParameter("userAge"));

        System.out.println("userId = " + userId);
        System.out.println("userName = " + userName);
        return "";
    }

    @PostMapping("/basic/join2")
    public String join(@RequestParam("userId") String id,
                       String userPw,
                       String userName,
                       int userAge,
                       @RequestParam("hobbies") ArrayList<String> hobbies) {
        //input태그의 name과 이름이 같아야함, @RequestParam 쓰면 이름 지정 가능
        System.out.println("/basic/2 요청이 들어옴!");

        System.out.println("userId = " + id);
        System.out.println("userName = " + userName);
        System.out.println("userAge = " + userAge);
        System.out.println("hobbies = " + hobbies);
        return "";
    }

    @PostMapping("/basic/join3")
    public String join(User user, Model model) {
        System.out.println("아이디: " + user.getUserId());
        System.out.println("이름: " + user.getUserName());
        System.out.println("취미: " + user.getHobbies());

        model.addAttribute("user", user);
        return "/request/result";
    }

    //화면쪽으로 서버의 데이터를 전달하는 방법
    @PostMapping("/model")
    public String modelBasic(Model model, int age) {
        // 클라이언트쪽으로 데이터를 greet이라는 이름으로 메롱을 담아서 전달
        model.addAttribute("greet", "메롱");
        model.addAttribute("myAge", age);

        int birthYear = LocalDate.now().getYear() - age + 1;
        model.addAttribute("birth", birthYear);

        return "request/model_study";
    }

    @GetMapping("/res-quiz")
    public String res() {
        return "/request/res-quiz";
    }

    @PostMapping("/response/quiz")
    public String quiz(String userId, String userPw, Model model) {
        System.out.println("로그인 요청 들어옴!");

        model.addAttribute("account", userId);
        if (userId.equals("kim123") && userPw.equals("kkk1234"))
            return "request/success";
        else
            return "request/fail";
    }




}
