<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 04.02.2025
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration JSP</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/reg" method="post">
    <label for="email"> E-mail:<br>
        <input type="email" name="email" id="email" required>
    </label><br>
    <label for="username"> Username:<br>
        <input type="text" name="username" id="username" required>
    </label><br>
    <label for="password"> Password:<br>
        <input type="password" name="password" id="password" required>
    </label><br>
    <button type="submit">Register</button>
</form>

<c:if test="${not empty requestScope.errors}">
    <c:forEach var="error" items="${requestScope.errors}">
        <p style="color: red">
                ${error.message}<br>
        </p>
    </c:forEach>
</c:if>
</body>
</html>
