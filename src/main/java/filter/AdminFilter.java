package filter;

import dto.read.ReadUserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/addCompany")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");
        if (user.getRole().equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        resp.sendRedirect("/companies");
    }
}
