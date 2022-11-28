<%--
  Created by IntelliJ IDEA.
  User: Tori
  Date: 02.11.2022
  Time: 23:06
  To change this template use File | Settings | File Templates.
  <p align="left"> <%= request.getAttribute("error_message") %></p>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>👁👁👁👁</title>
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>

    Произошла непредвиденная ошибка или введённые данные не соответствуют требованиям!
    <br>
    <br>
    <div class="form">
        <form method="post" action="index.jsp">
            <input type="submit" value='Вернуться' >
        </form>
    </div>
</body>
</html>
