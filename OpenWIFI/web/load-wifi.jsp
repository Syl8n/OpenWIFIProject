<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div>
        <h2><%= session.getAttribute("datanum") %>개의 WIFI 정보를 정상적으로 전달하였습니다.</h2>
        <a href="/">홈으로 가기</a>
    </div>
</body>
</html>
