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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/static/css/style.css">
    <title>Blog Listele</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container content">
    <c:forEach items="${blogList}" var="blogItem">
        <div class="card m-2 p-2">
            <div class="card-body">
                <h4 class="card-title">${blogItem.title}</h4>
                <p class="card-text">${blogItem.content}</p>
                <div class="row justify-content-end">
<%--                    <div class="col-md-2" >--%>
<%--                        <a class="link-secondary link-underline-opacity-25 link-underline-opacity-75-hover" href="#">Author: ${blogItem.user.username}</a>--%>
<%--                    </div>--%>
                    <div class="col-md-2">
                        <p class="card-text text-body-secondary">Last Modified: <fmt:formatDate value="${blogItem.lastChangeDate}" pattern="dd/MM/yyyy" /></p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
