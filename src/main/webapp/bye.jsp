<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <%
        int a = 10, b = 20;
    %>

    <% for (int i = 0; i < 3; i++) { %>
        <h1>jsp 테스트 중입니다~<%= a + b %></h1>
    <% } %>
</body>
</html>