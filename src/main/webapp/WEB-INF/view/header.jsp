<%--
  Created by IntelliJ IDEA.
  User: cpu_p
  Date: 15.06.2023
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <div class="col-md-3 mb-2">
        <a href="/" class="link-underline-opacity-0 link-body-emphasis fs-1 ms-5">
            Blog Sitesi
        </a>
    </div>

    <ul class="nav col-12 col-md-auto mb-2 mb-md-0">
        <li>
            <a href="${pageContext.request.contextPath}/" class="nav-link px-2">Anasayfa</a>
        </li>
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
    </div>
</header>
