<%--
  Created by IntelliJ IDEA.
  User: Burkay
  Date: 31.05.2023
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>User Listele</title>
</head>
<body>
<h1>User Listesi</h1>
<table>

    <tr>
        <th>ID</th>
        <th>Kullanıcı Adı</th>
        <th>Şifre</th>
        <th>E-Mail</th>
        <th>Aktif Mi?</th>
    </tr>

    ${partialView}

    <c:forEach items="${userList}" var="userItem" >
        <tr>
            <td>${userItem.userID}</td>
            <td>${userItem.username}</td>
            <td>${userItem.password}</td>
            <td>${userItem.email}</td>
            <td>${userItem.isActive}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
