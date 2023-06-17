<%--
  Created by IntelliJ IDEA.
  User: cpu_p
  Date: 31.05.2023
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Kaydol</title>
<%--    <script--%>
<%--            src="https://code.jquery.com/jquery-3.7.0.min.js"--%>
<%--            integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="--%>
<%--            crossorigin="anonymous"></script>--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
<div align="center">
    <span>${message}</span>
    <form:form method="post" action="/bm470/user/saveuser" modelAttribute="user">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Kaydol</h2></td>
            </tr>
            <tr>
                <td>Kullanıcı Adı:</td>
                <td><form:input path="username" id="username"/></td>
                <td><small>${usernameAvailability}</small></td>
            </tr>
            <tr>
                <td>Şifre:</td>
                <td><form:password path="password" id="password"/></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" id="email"/></td>
                <td><small>${emailAvailability}</small></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" id="register_button" value="Kaydet" />
            </tr>
        </table>
    </form:form>

    <!-- Modal -->
    <div class="modal fade" id="registeredModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Kayıt başarılı!
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="homepageButton">Anasayfaya Dön</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    const registerModal = new bootstrap.Modal('#registeredModal', {
        keyboard: false
    })

    document.getElementById("homepageButton").onclick = function () {
        location.href = "/bm470/";
    };

</script>

<c:if test="${message=='OK'}">
    <script type="text/javascript">
        registerModal.show()
    </script>
</c:if>


</body>
</html>