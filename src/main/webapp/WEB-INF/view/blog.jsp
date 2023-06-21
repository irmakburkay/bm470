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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/styles/style.css" rel="stylesheet">

    <script>
        $(document).ready(function () {
            $(".delete-click").click(function (event) {
                event.stopPropagation()
                var blogID = $(this).data("href")
                if (confirm("Blogu silmek istediğinize emin misiniz?") == true) {
                    $.post("/bm470/blog/delete", {"blogID": blogID}, function (data, status) {
                        alert("Status: " + status);
                        window.location = "/bm470/blog/" + blogID
                    })
                }
            })
        })
        $(document).ready(function () {
            $(".approve-click").click(function (event) {
                event.stopPropagation()
                var blogID = $(this).data("href")
                if (confirm("Blogu aktifleştirmek istediğinize emin misiniz?") == true) {
                    $.post("/bm470/blog/approve", {"blogID": blogID}, function (data, status) {
                        alert("Status: " + status);
                        window.location = "/bm470/blog/" + blogID
                    })
                }
            })
        })
    </script>

    <title>Blog Listele</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container content">
        <div class="card m-2 p-2">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h4 class="card-title">${blog.title}</h4>
                    </div>
                    <c:if test='${pageContext.session.getAttribute("loginUser")==blog.user}'>
                        <div class="col">
                        <div class="row justify-content-end">
                            <div class="col-auto">
                                <form action="/bm470/blog/update" method="post">
                                    <input type="hidden" name="blogID" value="${blog.blogID}"/>
                                    <button type="submit" class="btn btn-primary">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="24" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                            <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"></path>
                                        </svg>
                                    </button>
                                </form>
                            </div>
                            <div class="col-auto">
                                <c:choose>
                                    <c:when test="${blog.isActive==true}">
                                        <button type="button" class="btn btn-outline-danger delete-click" data-href="${blog.blogID}">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="24" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"></path>
                                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"></path>
                                            </svg>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-success approve-click" data-href="${blog.blogID}">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="24" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"></path>
                                            </svg>
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    </c:if>
                </div>
                <p class="card-text">${blog.content}</p>
                <div class="row justify-content-end">
                    <c:choose>
                        <c:when test="${pageContext.session.getAttribute('loginUser')==blog.user}">
                            <div class="col-auto">
                                <p class="card-text text-body-secondary">Created: <fmt:formatDate value="${blog.creationDate}" pattern="dd/MM/yyyy" /></p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-auto">
                                <c:choose>
                                    <c:when test='${blog.user.isActive==false}'>
                                        <p class="card-text text-body-secondary">Author: anonymous</p>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="link-secondary link-underline-opacity-25 link-underline-opacity-75-hover" onclick="event.stopPropagation()" href="/bm470/user/${blog.user.userID}">Author: ${blog.user.username}</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="col-auto">
                        <p class="card-text text-body-secondary">Last Modified: <fmt:formatDate value="${blog.lastChangeDate}" pattern="dd/MM/yyyy" /></p>
                    </div>
                </div>
            </div>
        </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
