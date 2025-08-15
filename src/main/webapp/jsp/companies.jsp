<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 03.02.2025
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Companies JSP</title>
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="header.jsp"%>
<h1>Companies</h1>
<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/addCompany" method="get">
        <button type="submit">Add company</button>
    </form>
</c:if>
<a href="${pageContext.request.contextPath}/rentals">
    <button>
    Rentals
    </button>
</a>
<ul>
    <c:forEach var="company" items="${requestScope.companies}">
        <li>
            <img height="50" width="50" src="/images/${company.imgKey}" alt="<HERE SHOULD BE IMAGE>">
            <a
               href="${pageContext.request.contextPath}/rentals?company_id=${company.id}">
                    ${company.name}
            </a>
        </li>
    </c:forEach>
</ul>

<%@ include file="footer.jsp"%>

</body>
</html>
