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
    <link href="${pageContext.request.contextPath}/resources/styles/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container content">
    <a href="${pageContext.request.contextPath}/user/${user.userID}" class="btn btn-secondary">Bloglarım</a>
    <a href="${pageContext.request.contextPath}/blog/post" class="btn btn-secondary">Blog Yaz</a>
    <a class="btn btn-danger user-delete" data-href="${user.userID}">Hesabı Sil</a>
</div>

<script>
    $(document).ready(function () {
        $(".user-delete").click(function (event) {
            event.stopPropagation()
            var userID = $(this).data("href")
            if (confirm("Hesabı silmek istediğinize emin misiniz?") == true) {
                $.post("/bm470/user/delete", {"userID": userID}, function (data, status) {
                    alert("Status: " + status);
                    window.location = "/bm470/"
                })
            }
        })
    })
</script>

<jsp:include page="footer.jsp"/>
</body>
</html>
