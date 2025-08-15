<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 08.02.2025
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add rental car</title>
    <meta charset="UTF-8">
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/addRental" method="post" enctype="multipart/form-data">
        <h1>New Rental</h1>
        <h3>Car info</h3>
        <label for="nameId"> Name:<br>
            <input type="text" name="name" id="nameId"><br>
        </label>
        <label for="companyId"> Company:<br>
            <select name="companyId" id="companyId">
                <c:forEach var="company" items="${requestScope.companies}">
                    <option value="${company.id}">${company.name}</option>
                </c:forEach>
            </select><br>
        </label>
        <label for="nameId"> Year of release:<br>
            <input type="date" name="yearRelease" id="yearReleaseId"><br>
        </label>
        <h3>Rental info</h3>
        <label for="priceId"> Price:<br>
            <input type="text" name="price" id="priceId"><br>
        </label>
        <label for="descriptionId"> Description:<br>
            <input type="text" name="description" id="descriptionId"><br>
        </label>
        <label for="imageId">Car's image: <br>
            <input type="file" name="image" id="imageId"><br>
        </label>
        <button type="submit">
            Add
        </button>
    </form>
</div>
<div>
    <form action="${pageContext.request.contextPath}/companies" method="get">
        <button type="submit">Cancel</button>
    </form>
</div>
<div>
    <c:if test="${not empty requestScope.errors}">
        <c:forEach var="error" items="${requestScope.errors}">
            <p style="color: red; font-size: 10px"> ${error.code} ${error.message}</p>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
