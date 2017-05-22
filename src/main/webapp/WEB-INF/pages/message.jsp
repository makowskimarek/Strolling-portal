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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Strolling meeting</title>

    <!-- meta data -->
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <meta name="description" content="Strolling meeting - walk together" />
    <meta name="keywords" content="Strolling, meet, meeting, together" />
    <meta name="author" content="RepubliC">

    <!--JQuerry-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Bootstrap -->
    <link href="resources/sources/css/bootstrap.min.css" rel="stylesheet">
    <script src="resources/sources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!--Own CSS-->
    <link href="resources/sources/styles/strolling.css" rel="stylesheet">

    <!--Own JS script-->
    <script src="resources/sources/js/formValidator.js" type="text/javascript"></script>
</head>
<body>

    <div class = "container-fluid login">

        <div class="window">

            <div class="formDisplay">

                <h3 class="header">
                    <% out.println(request.getAttribute("message")); %>
                </h3>

                <form:form method="POST" action="/">
                    <button class="btn btn-lg btn-primary btn-block" type="Submit">Ok</button>
                </form:form>
            </div>
        </div>
    </div>

</body>
</html>
