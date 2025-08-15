<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 06.02.2025
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
</head>
<body>
    <div style="background-color: #dedede">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">
                Logout
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/profile" method="get">
            <button TYPE="submit">
                Profile
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/addRental" method="get">
            <button type="submit">
                Add Car-Rental
            </button>
        </form>
        <hr style="color: black">
    </div>
</body>
</html>
