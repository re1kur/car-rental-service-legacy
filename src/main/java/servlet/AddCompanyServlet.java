package servlet;

import dto.write.CreateCompanyDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CompanyService;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/addCompany")
public class AddCompanyServlet extends HttpServlet {
    private final CompanyService companyService = CompanyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/addCompany.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateCompanyDto company = CreateCompanyDto.builder()
                .name(req.getParameter("name"))
                .image(req.getPart("image"))
                .build();
        companyService.create(company);
        resp.sendRedirect("/companies");
    }
}
