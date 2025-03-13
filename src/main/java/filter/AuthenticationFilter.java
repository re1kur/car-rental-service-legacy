package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final String LOGIN_PAGE = "/auth";
    private static final String USER_ATTRIBUTE = "user";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        if (isExcludedURI(req.getRequestURI())) {
            filterChain.doFilter(req, resp);
            return;
        }
        Object user = req.getSession().getAttribute(USER_ATTRIBUTE);

        if (user != null) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(LOGIN_PAGE);
        }
    }


    private boolean isExcludedURI(String uri) {
        return uri.startsWith("/auth") || uri.startsWith("/reg");
    }
}
