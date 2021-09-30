<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>

<style>
    label {
        display: block;
    }
</style>
</head>
<body>

    <h1>BasicController를 이용한 요청 처리</h1>
    <p>컨트롤러 테스트중입니다.</p>

    <form action="/basic/join3" method="POST">
        <label>
            # 아이디: <input type="text" name="userId">
        </label>
        <label>
            # 비밀번호: <input type="password" name="userPw">
        </label>
        <label>
            # 이름: <input type="text" name="userName">
        </label>
        <label>
            # 나이: <input type="text" name="userAge">
        </label>
        <label>
            # 취미:
            <input type="checkbox" name ="hobbies" value="축구">축구
            <input type="checkbox" name ="hobbies" value="노래하기">노래하기
            <input type="checkbox" name ="hobbies" value="잠자기">잠자기
        </label>

        <button type="submit">회원가입</button>
    </form>
</body>
</html>