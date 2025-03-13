package servlet;

import dto.read.ReadCompanyDto;
import dto.write.CreateCarDto;
import dto.write.CreateRentalDto;
import dto.read.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CompanyService;
import service.RentalService;
import util.exception.ValidationException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/addRental")
public class AddRentalServlet extends HttpServlet {
    private final RentalService rentalService = RentalService.getInstance();
    private final CompanyService companyService = CompanyService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReadCompanyDto> companies = companyService.readAll();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/addRental.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto owner = (ReadUserDto) req.getSession().getAttribute("user");
        CreateCarDto createCarDto = CreateCarDto.builder()
                .yearRelease(LocalDate.parse(req.getParameter("yearRelease")))
                .name(req.getParameter("name"))
                .companyId(Integer.parseInt(req.getParameter("companyId")))
                .build();
        CreateRentalDto createRentalDto = CreateRentalDto.builder()
                .car(createCarDto)
                .image(req.getPart("image"))
                .price(Integer.parseInt(req.getParameter("price")))
                .description(req.getParameter("description"))
                .ownerId(owner.getId())
                .build();
        try {
            rentalService.create(createRentalDto);
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
            return;
        }
        resp.sendRedirect("/rentals");
    }

}
