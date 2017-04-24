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
<h3>Person : </h3>

<%
    ArrayList<String> list = (ArrayList<String>) request.getAttribute("personBase");

    for(String person : list) {
        out.println(person);

%>
<br />
<%
    }
%>

<form:form method = "POST" action = "home.jsp">
    <tr>
        <button type="submit">Back</button>
    </tr>
</form:form>
</body>
</html>
