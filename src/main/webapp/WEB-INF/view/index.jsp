<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/static/css/style.css">

<%--    js script--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function paging(pageNumber, maxResult) {
            var baseUrl = "/bm470/page"
            var q1 = "pageNumber=" + pageNumber
            var q2 = "maxResult=" + maxResult
            var newUrl = baseUrl + "?" + q1 + "&" + q2
            $(".container").fadeOut(250, function () {
                window.location = newUrl
            })
        }
        $(document).ready(function () {
            $(".card-click").click(function () {
                var baseUrl = "/bm470/blog/"
                var id = $(this).data("href")
                alert("af")
                window.location = baseUrl + id
            })
        })
        $(document).ready(function () {
            $(".paging-click").click(function () {
                if ("${pageNumber}"!==$(this).data("href")) {
                    paging($(this).data("href"), $(".paging-change").val())
                }
            })
        })
        $(document).ready(function () {
            $(".paging-change").change(function () {
                paging(${pageNumber}, $(".paging-change").val())
            })
        })
        $(document).ready(function () {
            $(".delete-click").click(function (event) {
                event.stopPropagation()
                var blogID = $(this).data("href")
                if (confirm("Blogu silmek istediğinize emin misiniz?") == true) {
                    $.post("http://localhost:9090/bm470/blog/delete", {"blogID": blogID}, function (data, status) {
                        alert("Status: " + status);
                        window.location = data
                    })
                }
            })
        })
    </script>

</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container content" style="background-color: azure; width: 70%; height: max-content">

    <div class="row">

        <div class="col-md-2">
            <label for="page-size-select" class="form-label">Sayfa Boyutu:</label>
        </div>
        <div class="col-md-2">
            <select id="page-size-select" class="form-select paging-change">
                <c:forTokens  var="i" items="5,10,20" delims=",">
                    <option value="${i}" <c:if test='${pageContext.session.getAttribute("maxResult")==i}'>selected</c:if>>${i}</option>
                </c:forTokens>
            </select>
        </div>

        <div class="col align-self-end">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end">
                    <li class="page-item
                        <c:choose>
                            <c:when test='${pageNumber==0}'>disabled</c:when>
                            <c:otherwise>paging-click</c:otherwise>
                        </c:choose>"
                        data-href="${pageNumber-1}">
                        <p class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </p>
                    </li>
                    <c:forEach var = "i" begin = "0" end = "${pageSize-1}">
                        <li class="page-item paging-click
                            <c:if test='${pageNumber==i}'>active</c:if>"
                            data-href="${i}">
                            <p class="page-link">${i+1}</p>
                        </li>
                    </c:forEach>
                    <li class="page-item <c:choose><c:when test='${pageNumber==pageSize-1}'>disabled</c:when><c:otherwise>paging-click</c:otherwise></c:choose>" data-href="${pageNumber+1}">
                        <p class="page-link" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </p>
                    </li>
                </ul>
            </nav>
        </div>

    </div>

    <c:forEach items="${blogList}" var="blogItem">
        <div class="card m-2 p-2 card-click" data-href="${blogItem.blogID}">
            <div class="card-body">
                <h4 class="card-title">${blogItem.title}</h4>
                <p class="card-text">
                    <c:choose>
                        <c:when test='${fn:length(blogItem.content) > 100}'>${fn:substring(blogItem.content, 0, 100)} ...</c:when>
                        <c:otherwise>${blogItem.content}</c:otherwise>
                    </c:choose>
                </p>
                <div class="row">
                    <c:if test='${pageContext.session.getAttribute("loginUser")==blogItem.user}'>
                        <div class="col">
                            <div class="row justify-content-start">
                                <div class="col-auto">
                                    <form action="/bm470/blog/update" method="post">
                                        <input type="hidden" name="blogID" value="${blogItem.blogID}"/>
                                        <button type="submit" onclick="event.stopPropagation()" class="btn btn-primary">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="24" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                                <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"></path>
                                            </svg>
                                        </button>
                                    </form>
                                </div>
                                <div class="col-auto">
                                    <button type="button" class="btn btn-outline-danger delete-click" data-href="${blogItem.blogID}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="24" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"></path>
                                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"></path>
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test='${blogItem.blogID!=2}'>
                        <div class="col">
                            <div class="row justify-content-end">
                                <div class="col-auto" >
                                    <c:choose>
                                        <c:when test='${blogItem.user.username=="anonymous"}'>
                                            <p class="card-text text-body-secondary">Author: ${blogItem.user.username}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="link-secondary link-underline-opacity-25 link-underline-opacity-75-hover" onclick="event.stopPropagation()" href="/bm470/user/${blogItem.user.userID}">Author: ${blogItem.user.username}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text text-body-secondary">Last Modified: <fmt:formatDate value="${blogItem.lastChangeDate}" pattern="dd/MM/yyyy" /></p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>

            </div>
        </div>
    </c:forEach>

    <div class="row">
        <div class="col align-self-end">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end">
                    <li class="page-item
                        <c:choose>
                            <c:when test='${pageNumber==0}'>disabled</c:when>
                            <c:otherwise>paging-click</c:otherwise>
                        </c:choose>"
                        data-href="${pageNumber-1}">
                        <p class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </p>
                    </li>
                    <c:forEach var = "i" begin = "0" end = "${pageSize-1}">
                        <li class="page-item paging-click <c:if test='${pageNumber==i}'>active</c:if>" data-href="${i}"><p class="page-link">${i+1}</p></li>
                    </c:forEach>
                    <li class="page-item
                        <c:choose>
                            <c:when test='${pageNumber==pageSize-1}'>disabled</c:when>
                            <c:otherwise>paging-click</c:otherwise>
                        </c:choose>"
                        data-href="${pageNumber+1}">
                        <p class="page-link" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </p>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
