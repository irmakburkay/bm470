<%--
  Created by IntelliJ IDEA.
  User: cpu_p
  Date: 31.05.2023
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
  <title>Blog Yaz</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
  <link href="${pageContext.request.contextPath}/resources/styles/style.css" rel="stylesheet">
</head>
<body>
<div class="container content">
  <form:form method="post" action="/bm470/blog/saveblog" modelAttribute="blog">
    <form:hidden path="blogID" id="blogID"/>
    <form:hidden path="isActive" id="isActive"/>
    <legend>Yeni Blog Yazısı</legend>

    <div class="mb-3">
      <label class="form-label">Başlık: </label>
      <form:input path="title" id="title" cssClass="form-control"/>
      <p class="error">${titleValidMessage}</p>
    </div>

    <div class="mb-3">
      <label class="form-label">İçerik:</label>
      <form:textarea path="content" id="content" cssClass="form-control" rows="10"/>
      <p class="error">${contentValidMessage}</p>
    </div>

    <input type="submit" value="Kaydet" class="btn btn-primary float-end"/>
  </form:form>
</div>
</body>
</html>
