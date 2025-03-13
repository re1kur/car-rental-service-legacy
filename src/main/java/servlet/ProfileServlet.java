package servlet;

import dto.write.PersonalInfoDto;
import dto.read.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonalInfoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final PersonalInfoService personalInfoService = PersonalInfoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");
        Optional<PersonalInfoDto> maybePersonalInfo = personalInfoService.read(user);
        req.setAttribute("personalInfo", maybePersonalInfo.orElse(null));
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                .forward(req, resp);
    }
}
