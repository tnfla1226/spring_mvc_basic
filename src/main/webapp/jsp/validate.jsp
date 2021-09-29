<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>


<% 
    //나이를 읽어들여야 함
    String ageStr = request.getParameter("age");
    int age = Integer.parseInt(ageStr);

    String msg = (age > 19) ? "성인" : "미성년자";
%>

    <h1>당신은 <%= msg %> 입니다.</h1>

</body>
</html>