<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>성적관리 프로그램</title>

<style>
    label {
        display: block;
    }
    .score-list > li {
        margin-bottom: 10px;
    }
    .score-list > li:first-child {
        font-size: 1.2em;
        color: blue;
        font-weight: 700;
        border-bottom: 1px solid skyblue;
    }
    .del-btn {
        width: 5px;
        height: 5px;
        text-decoration: none;
        color: #fff;
        background-color: red;
        border-radius: 5px;
        margin: 5px;
    }

</style>

</head>
<body>

    <h1>시험 점수 등록</h1>
    <form action="/score/register" method="POST">

        <label>
            # 이름: <input type="text" name="name">
        </label>
        <label>
            # 국어: <input type="text" name="kor">
        </label>
        <label>
            # 영어: <input type="text" name="eng">
        </label>
        <label>
            # 수학: <input type="text" name="math">
        </label>
        <label>
            <button type="submit">확인</button>
            <button id="go-home" type="button">홈화면으로</button>
        </label>
    </form>

    <hr>

    <ul class="score-list">
        <li>총 학생 수: ${scoreList.size()}명 </li>
        
        <c:forEach var="s" items="${scoreList}">
            <li>
                # 학번: ${s.stuNum}, 이름: <a href="/score/detail">${s.name}</a>, 국어: ${s.kor}점, 영어: ${s.eng}점, 수학: ${s.math}점, 총점: ${s.total}점, 평균: ${s.average}
                <a class="del-btn" href="/score/delete?stuNum=${s.stuNum}">삭제</a>
            </li>
        </c:forEach>
        <li>총 학생 수: xxx명 </li>

        <li>
            # 학번: xxx, 이름: xxx, 국어: xx점, 영어: xx점, 수학: xx점, 총점: xx점, 평균: xx점
        </li>
    </ul>

    <script>
        const home = document.getElementById('go-home');
        home.addEventListener('click', e => {
            location.href = '/';    //링크이동기능
        });
    </script>

</body>
</html>