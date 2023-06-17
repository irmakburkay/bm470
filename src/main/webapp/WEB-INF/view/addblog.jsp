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
  <title>Title</title>
</head>
<body>
<div align="center">
  <form:form method="post" action="/bm470/blog/saveblog" modelAttribute="blog">
    <table border="0">
      <tr>
        <td colspan="2" align="center"><h2>Yeni Blog Yazısı</h2></td>
      </tr>
      <tr>
        <td>Başlık:</td>
        <td><form:input path="title" id="title"/>
      </tr>
      <tr>
        <td>İçerik:</td>
        <td><form:textarea path="content" id="content"/>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="Kaydet" />
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>
