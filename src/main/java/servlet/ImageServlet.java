package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getRequestURI().replace("/images/", "");

        imageService.download(imagePath)
                .ifPresentOrElse(imageStream -> {
                            resp.setContentType("image/jpeg");
                            writeImage(imageStream, resp);
                        },
                        () -> resp.setStatus(404));
    }

    @SneakyThrows
    private void writeImage(InputStream imageStream, HttpServletResponse resp) {
        try (imageStream;
             OutputStream out = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = imageStream.read()) != -1) {
                out.write(currentByte);
            }
        }
    }
}
