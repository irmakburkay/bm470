<%--
  Created by IntelliJ IDEA.
  User: Burkay
  Date: 31.05.2023
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${user.username} Blogları</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/styles/style.css" rel="stylesheet">

    <script>
        $(document).ready(function () {
            $(".card-click").click(function () {
                var baseUrl = "/bm470/blog/"
                var id = $(this).data("href")
                alert("card click: " + id)
                window.location = baseUrl + id
            })
        })
    </script>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="container content">
        <h1>Ev Sahibi: ${user.username}</h1>

        ${lurker}

        <c:forEach items="${blogList}" var="blogItem">
            <div class="card m-2 p-2 card-click" data-href="${blogItem.blogID}">
                <div class="card-body">
                    <h4 class="card-title">${blogItem.title}</h4>
                    <p class="card-text">${blogItem.content}</p>
                    <div class="row justify-content-end">
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
