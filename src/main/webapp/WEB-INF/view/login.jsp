<%--
  Created by IntelliJ IDEA.
  User: cpu_p
  Date: 14.06.2023
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Giriş Yap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/resources/styles/style.css" rel="stylesheet">
</head>
<body>
<div align="center">
    <form:form action="/bm470/user/loginUser" method="post" modelAttribute="user">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Giriş Yap</h2></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><span class="error">${login_message}</span></td>
            </tr>
            <tr>
                <td>Kullanıcı Adı:</td>
                <td><form:input path="username" id="username"/>
            </tr>
            <tr>
                <td>Şifre:</td>
                <td><form:password path="password" id="password"/>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Giriş Yap" />
            </tr>
        </table>
    </form:form>
</div>


</body>
</html>
