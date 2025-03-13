package servlet;

import dto.read.ReadCompanyDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CompanyService;

import java.io.IOException;
import java.util.List;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private final CompanyService companyService = CompanyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReadCompanyDto> readCompanyDtos = companyService.readAll();
        req.setAttribute("companies", readCompanyDtos);
        req.setAttribute("companiesSize", readCompanyDtos.size());
        req.getRequestDispatcher("/WEB-INF/jsp/companies.jsp")
                .forward(req, resp);
    }
}
