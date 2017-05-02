<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Rafal
  Date: 03.04.2017
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Walker - Start</title>
</head>
<body>
<h3>Profile : </h3>

<% out.println(request.getAttribute("user")); %>

<form:form method = "POST" action = "index.jsp">
    <tr>
        <button type="submit">Logout</button>
    </tr>
</form:form>
</body>
</html>
