package Auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/protected/admin.html")
public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Object isAdmin = req.getSession().getAttribute("admin");

        if (isAdmin != null && isAdmin.equals(Boolean.TRUE)) {
            chain.doFilter(request, response); // Zugriff erlaubt
        } else {
            res.sendRedirect("/public/admin-login.html"); // Kein Zugriff → weiterleiten
        }
    }
}
