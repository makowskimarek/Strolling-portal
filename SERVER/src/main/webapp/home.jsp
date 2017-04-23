<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Swiety
  Date: 21.03.2017
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Walker - Home</title>
</head>
<body>
<tr>
    Show all users
</tr>
<form:form method = "POST" action = "/Start">
    <tr>
        <button type="submit">Show</button>
    </tr>
</form:form>
<form:form method = "POST" action = "add.jsp">
    <tr>
        <button type="submit">Register</button>
    </tr>
</form:form>
</body>
</html>
