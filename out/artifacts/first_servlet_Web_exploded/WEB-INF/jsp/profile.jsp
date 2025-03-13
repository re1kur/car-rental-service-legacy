<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 06.02.2025
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${sessionScope.user.username} profile</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/companies" method="get">
    <button type="submit">
        Companies
    </button>
</form>

<div>
    <h3>Username:</h3>
    <span>${sessionScope.user.username}</span>
    <h3>Email:</h3>
    <span>${sessionScope.user.email}</span>
    <br>
    <h3>Personal information:</h3>
    <div>
        <form action="${pageContext.request.contextPath}/edit" method="get">
            <button>Edit personal information</button>
        </form>
    </div>
    <c:if test="${empty requestScope.personalInfo}">
        Nothing entered.
    </c:if>
    <c:if test="${not empty requestScope.personalInfo}">
        <div>
            <h4>Name:</h4>
            <span>
            <c:choose>
                <c:when test="${requestScope.personalInfo.name == null}">
                    Not entered
                </c:when>
                <c:when test="${requestScope.personalInfo.name != null}">
                    ${requestScope.personalInfo.name}
                </c:when>
            </c:choose>
        </span>
            <h4>Birthday:</h4>
            <span>
        <c:choose>
            <c:when test="${requestScope.personalInfo.birthday == null}">
                Not entered
            </c:when>
            <c:when test="${requestScope.personalInfo.birthday != null}">
                ${requestScope.personalInfo.birthday}
            </c:when>
        </c:choose>
        </span>
            <h4>Passport number:</h4>
            <span>
            <c:choose>
                <c:when test="${requestScope.personalInfo.passNo == null}">
                    Not entered
                </c:when>
                <c:when test="${requestScope.personalInfo.passNo != null}">
                    ${requestScope.personalInfo.passNo}
                </c:when>
            </c:choose>
        </span>
        </div>
    </c:if>
</div>



</body>
</html>
