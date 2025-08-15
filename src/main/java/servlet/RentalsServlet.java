package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RentalService;

import java.io.IOException;

@WebServlet("/rentals")
public class RentalsServlet extends HttpServlet {
    private final RentalService rentalService = RentalService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyId = req.getParameter("company_id");
        req.setAttribute("rentals",
                companyId == null ? rentalService.readAll() : rentalService.readByCompanyId(Integer.parseInt(companyId)));
        req.getRequestDispatcher("/jsp/rentals.jsp")
                .forward(req, resp);
    }

}
