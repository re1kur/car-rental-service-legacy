<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 10.02.2025
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Rent JSP</title>
    <meta charset="UTF-8">
</head>
<body>
<span style="
            font-weight: bold;
            font-size: 20px;">
                    ${requestScope.rental.companyName} ${requestScope.rental.carName}
            </span> <br>
<img height="125" width="250" src="/images/${requestScope.rental.imgKey}" alt="<HERE SHOULD BE IMAGE>"> <br>
<span style="margin: 5px">
                Year of release: ${requestScope.rental.yearRelease}</span><br>
<c:if test="${requestScope.rental.description != null}">
                <span style="margin: 5px">
                    Description:</span><br>
    <span style="font-style: italic;
                margin: 15px">${requestScope.rental.description}</span><br>
</c:if>
<span style="margin: 5px">Price per day: ${requestScope.rental.price}$</span>
<form action="${pageContext.request.contextPath}/rent?id=${requestScope.rental.id}" method="post">
    <label for="startDateId"> Date of start rental: <br>
    <input type="date" name="startDate" id="startDateId" required>
    </label> <br>
    <label for="endDateId"> End of start rental: <br>
        <input type="date" name="endDate" id="endDateId" required>
    </label> <br>
    <button type="submit">
        Rent
    </button>
</form>
</body>
</html>
