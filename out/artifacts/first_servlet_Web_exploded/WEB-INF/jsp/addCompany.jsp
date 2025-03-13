<%--
  Created by IntelliJ IDEA.
  User: reikur
  Date: 10.02.2025
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add company JSP</title>
</head>
<body>
<h1>New Company</h1>
<form action="${pageContext.request.contextPath}/addCompany" method="post"  enctype="multipart/form-data">
    <label for="nameId">Name of company: <br>
    <input type="text" name="name" id="nameId">
    </label> <br>
    <label for="imageId">Image of company: <br>
    <input type="file" name="image" id="imageId">
    </label> <br>

    <button type="submit">
        Add
    </button>
    <br>
</form>

<form action="${pageContext.request.contextPath}/companies" method="get">
    <button type="submit">
        Cancel
    </button>
</form>

</body>
</html>
