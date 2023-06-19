<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                alert("card click: " + id)
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
                    <option value="${i}" <c:if test='${maxResult==i}'>selected</c:if>>${i}</option>
                </c:forTokens>
            </select>
        </div>

        <div class="col align-self-end">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end">
                    <li class="page-item <c:choose><c:when test='${pageNumber==0}'>disabled</c:when><c:otherwise>paging-click</c:otherwise></c:choose>" data-href="${pageNumber-1}">
                        <p class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </p>
                    </li>
                    <c:forEach var = "i" begin = "0" end = "${pageSize-1}">
                        <li class="page-item paging-click <c:if test='${pageNumber==i}'>active</c:if>" data-href="${i}"><p class="page-link">${i+1}</p></li>
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
                <p class="card-text">${blogItem.content}</p>
                <div class="row justify-content-end">
                    <div class="col-md-2" >
                        <a class="link-secondary link-underline-opacity-25 link-underline-opacity-75-hover" href="/bm470/user/${blogItem.user.userID}">Author: ${blogItem.user.username}</a>
                    </div>
                    <div class="col-md-2">
                        <p class="card-text text-body-secondary">Last Modified: <fmt:formatDate value="${blogItem.lastChangeDate}" pattern="dd/MM/yyyy" /></p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="row">
        <div class="col align-self-end">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end">
                    <li class="page-item <c:choose><c:when test='${pageNumber==0}'>disabled</c:when><c:otherwise>paging-click</c:otherwise></c:choose>" data-href="${pageNumber-1}">
                        <p class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </p>
                    </li>
                    <c:forEach var = "i" begin = "0" end = "${pageSize-1}">
                        <li class="page-item paging-click <c:if test='${pageNumber==i}'>active</c:if>" data-href="${i}"><p class="page-link">${i+1}</p></li>
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

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
