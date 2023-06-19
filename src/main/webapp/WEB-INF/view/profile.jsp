<%--
  Created by IntelliJ IDEA.
  User: cpu_p
  Date: 16.06.2023
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    <%-- css style --%>
    <style>
        .content {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: azure;
            width: 70%;
            height: max-content
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container content">
    <%= session.getAttribute("loginUser")%>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
