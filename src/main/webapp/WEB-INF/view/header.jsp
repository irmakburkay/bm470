<%--
  Created by IntelliJ IDEA.
  User: cpu_p
  Date: 15.06.2023
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <form action="/bm470/user/${login_logout_link}" method="post">
                        <input class="btn btn-outline-primary me-2" type="submit" value="${login_logout_text}">
                    </form>
                </td>
                <td>
                    <form action="/bm470/user/${register_profile_link}" method="post">
                        <input class="btn btn-primary" type="submit" value="${register_profile_text}">
                    </form>
                </td>
            </tr>
        </table>
        <%--        <button type="button" class="btn btn-outline-primary me-2" id="btn_login">Giriş Yap</button>--%>
        <%--        <button type="button" class="btn btn-primary" id="btn_kaydol">Kaydol</button>--%>
    </div>
</header>
