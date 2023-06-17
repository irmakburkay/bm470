package tr.edu.duzce.mf.bm.bm470.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import tr.edu.duzce.mf.bm.bm470.model.User;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.getWriter().print("<h1>Please Login</h1>");
            return false;
        } else {
            return true;
        }
    }
}
