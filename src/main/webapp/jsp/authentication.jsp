<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 05.02.2025
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login JSP</title>
    <meta charset="UTF-8">
</head>
<body>
<form action="${pageContext.request.contextPath}/auth" method="post">
    <label for="emailId">Email:<br>
        <input type="text" name="email" id="emailId"  value="${param.email}">
    </label><br>
    <label for="passwordId">Password:<br>
        <input type="password" name="password" id="passwordId">
    </label><br>
    <button type="submit">Login</button>
</form>
<form action="${pageContext.request.contextPath}/reg" method="get">
    <button type="submit">
        Register
    </button>
</form>
    <c:if test="${not empty param.error}">
        <div>
            <p style="color: red">
                The email or password is not correct!
            </p>
        </div>
    </c:if>
</body>
</html>
