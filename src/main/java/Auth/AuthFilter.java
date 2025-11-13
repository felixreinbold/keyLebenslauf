package Auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/protected/index.html")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Object userKey = req.getSession().getAttribute("userKey");

        if (userKey != null) {
            chain.doFilter(request, response); // Zugriff erlaubt
        } else {
            res.sendRedirect("/public/login.html"); // Kein Zugriff → zurück zum Login
        }
    }
}
