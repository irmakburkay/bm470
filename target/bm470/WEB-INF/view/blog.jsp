<%--
  Created by IntelliJ IDEA.
  User: Burkay
  Date: 21.05.2023
  Time: 01:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listeleme JSP</title>
</head>
<body>
<h1>Blog Listesi</h1>
<table>

    <tr>
        <th>ID</th>
        <th>Başlık</th>
        <th>İçerik</th>
        <th>Tarih</th>
    </tr>

    ${partialView}

    <c:forEach items="${blogList}" var="blogItem" >
        <tr>
            <td>${blogItem.id}</td>
            <td>${blogItem.baslik}</td>
            <td>${blogItem.icerik}</td>
            <td><fmt:formatDate value="${blogItem.tarih}" pattern="dd/MM/yyyy" /> </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
