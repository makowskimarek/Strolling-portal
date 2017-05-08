<%--
  Created by IntelliJ IDEA.
  User: Rafal
  Date: 01.05.2017
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<h3>
    <% out.println(request.getAttribute("message")); %>
</h3>

<form:form method = "POST" action = "/">
    <tr>
        <button type="submit">OK</button>
    </tr>
</form:form>
</body>
</html>
