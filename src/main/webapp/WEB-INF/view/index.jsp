<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

<%--    css style--%>
    <style>
        .table-row{
            cursor:pointer;
        }
    </style>

<%--    js script--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.table-row').on("click", function() {
                var baseUrl = "http://localhost:9090/bm470/blog/";
                var id = $(this).data("href");
                window.location = baseUrl + id;
            });
        });
    </script>

</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <div class="col-md-3 mb-2 mb-md-0">
        <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
            <svg class="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>
    </div>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
        <li><a href="#" class="nav-link px-2">Features</a></li>
        <li><a href="#" class="nav-link px-2">Pricing</a></li>
        <li><a href="#" class="nav-link px-2">FAQs</a></li>
        <li><a href="#" class="nav-link px-2">About</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <table>
            <tr>
                <td>
                    <form action="/bm470/user/login" method="post">
                        <input class="btn btn-outline-primary me-2" type="submit" value="Giriş Yap">
                    </form>
                </td>
                <td>
                    <form action="/bm470/user/register" method="post">
                        <input class="btn btn-primary" type="submit" value="Kaydol">
                    </form>
                </td>
            </tr>
        </table>
        <%--        <button type="button" class="btn btn-outline-primary me-2" id="btn_login">Giriş Yap</button>--%>
        <%--        <button type="button" class="btn btn-primary" id="btn_kaydol">Kaydol</button>--%>
    </div>
</header>

<div class="container" style="background-color: azure; width: 70%; height: max-content">

    <div class="row mb-3">

        <div class="col">
            <label for="page-size-select" class="form-label">Sayfa Boyutu:</label>
            <select id="page-size-select" class="form-select">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
            </select>
        </div>

        <div>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

    </div>


    <table class="table table-striped table-hover">
        <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">title</th>
                <th scope="col">content</th>
                <th scope="col">isActive</th>
                <th scope="col">date1</th>
                <th scope="col">date2</th>
                <th scope="col">username</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${blogList}" var="blogItem" >
            <tr class="table-row" data-href="${blogItem.blogID}">
                <th scope="row">${blogItem.blogID}</th>
                <td>${blogItem.title}</td>
                <td>${blogItem.content}</td>
                <td>${blogItem.isActive}</td>
                <td><fmt:formatDate value="${blogItem.creationDate}" pattern="dd/MM/yyyy" /> </td>
                <td><fmt:formatDate value="${blogItem.lastChangeDate}" pattern="dd/MM/yyyy" /> </td>
                <td>${blogItem.user.username}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
