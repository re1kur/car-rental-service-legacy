package servlet;

import dto.read.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.UserService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/authentication.jsp")
                .forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<ReadUserDto> read = userService.read(req.getParameter("email"), req.getParameter("password"));
        read.ifPresentOrElse(
                readUserDto -> onAuthSuccess(readUserDto, req, resp),
                () -> onAuthFail(req, resp)

        );


    }

    @SneakyThrows
    private void onAuthFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/auth?error=true&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onAuthSuccess(ReadUserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);

        resp.sendRedirect("/companies");
    }
}
