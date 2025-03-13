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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private final PersonalInfoService personalInfoService = PersonalInfoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");
        Optional<PersonalInfoDto> info = personalInfoService.read(user);
        req.setAttribute("personalInfo", info.orElse(null));
        req.getRequestDispatcher("/WEB-INF/jsp/editPersonalInfo.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");

        personalInfoService.create(PersonalInfoDto.builder()
                        .id(user.getId())
                        .name(req.getParameter("name"))
                        .passNo(req.getParameter("passNo"))
                        .birthday(req.getParameter("birthday"))
                .build());
        resp.sendRedirect("/profile");
    }
}
