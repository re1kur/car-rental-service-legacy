package servlet;

import dto.read.ReadRentalDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RentalService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/rent")
public class RentServlet extends HttpServlet {
    private final RentalService rentalService = RentalService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Optional<ReadRentalDto> maybeRental = rentalService.read(id);
        if (maybeRental.isPresent()) {
            req.setAttribute("rental", maybeRental.get());
            req.getRequestDispatcher("/jsp/rent.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/companies");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("clicked");
        resp.sendRedirect("/companies");
    }
}
