<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 06.02.2025
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Information</title>
    <meta charset="UTF-8">
</head>
<body>
<form action="${pageContext.request.contextPath}/edit" method="post">
    <label for="nameId">Name: <br>
        <input type="text" name="name" id="nameId" value="${requestScope.personalInfo.name}">
    </label><br>

    <label for="birthdayId">Birthday: <br>
        <input type="date" name="birthday" id="birthdayId" value="${requestScope.personalInfo.birthday}">
    </label><br>

    <label for="passNoId">Passport number:<br>
        <input type="text" name="passNo" id="passNoId" value="${requestScope.personalInfo.passNo}">
    </label><br>
    <button type="submit">
        Edit
    </button>
</form>
<br>
<form action="${pageContext.request.contextPath}/profile" method="get">
    <button type="submit">
        Cancel
    </button>
</form>
</body>
</html>
