<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 03.02.2025
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rentals JSP</title>
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="header.jsp" %>

<h1>Rentals</h1>

<form action="${pageContext.request.contextPath}/companies" method="get">
    <button type="submit">
        Companies
    </button>
</form>

<ul>
    <c:forEach var="rental" items="${requestScope.rentals}">
        <br>
        <li>
            <span style="
            font-weight: bold;
            font-size: 20px;">
                    ${rental.companyName} ${rental.carName}
            </span> <br>
            <img height="125" width="250" src="/images/${rental.imgKey}" alt="<HERE SHOULD BE IMAGE>"> <br>
            <span style="margin: 5px">
                Year of release: ${rental.yearRelease}</span><br>
            <c:if test="${rental.description != null}">
                <span style="margin: 5px">
                    Description:</span><br>
                <span style="font-style: italic;
                margin: 15px">${rental.description}</span><br>
            </c:if>
            <span style="margin: 5px">Price: ${rental.price}$</span> <br>
            <a href="${pageContext.request.contextPath}/rent?id=${rental.id}">
                <button>
                    Rent
                </button>
            </a>
        </li>
    </c:forEach>
</ul>
<%@ include file="footer.jsp" %>
</body>
</html>
