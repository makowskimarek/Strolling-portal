<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafal
  Date: 04.04.2017
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<table>
    <form:form method = "POST" action = "/Register">
        <tr>
            <td>Nick</td>
            <td>><input type="text" name=nick id="nick"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td>><input type="password" name=password id="password"></td>
        </tr>
        <tr>
            <td>Mail</td>
            <td>><input type="text" name=mail id="mail"></td>
        </tr>
            <tr>
                <td>
                <button type="submit">Register</button>
                </td>
            </tr>
    </form:form>
    <form:form method = "POST" action = "home.jsp">
        <tr>
            <td>
                <button type="submit">Back</button>
            </td>
        </tr>
    </form:form>

</table>


</body>
</html>
