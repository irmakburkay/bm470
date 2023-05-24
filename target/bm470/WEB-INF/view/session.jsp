<%--
  Created by IntelliJ IDEA.
  User: Burkay
  Date: 17.05.2023
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet'den Merhaba!</title>
</head>
<body>
<h1>Oturum</h1>
<p>Bağlantı Adresi: <strong><%= request.getRemoteAddr() %></strong></p>
<p>İstek URI: <strong><%= request.getRequestURI() %></strong></p>
<p>Protokol: <strong><%= request.getProtocol() %></strong></p>
<p>İstek Metodu: <strong><%= request.getMethod() %></strong></p>
<p>Session Id: <strong><%= session.getId()%></strong></p>
<p>Session Oluşturulma Zamanı: <strong>${creationTime}</strong></p>
<p>Session Son İstek Zamanı: <strong>${lastAccessedTime}</strong></p>
<p>Session Yeni Mi?: <strong><% if(session.isNew()) { %> Evet <% } else { %> Hayır <% } %></strong></p>
</body>
</html>

