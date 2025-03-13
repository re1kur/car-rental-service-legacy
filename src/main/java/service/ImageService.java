package service;

import lombok.Getter;
import lombok.SneakyThrows;
import util.PropertiesUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class ImageService {
    @Getter
    private static final ImageService instance = new ImageService();
    private static final String basePath = PropertiesUtil.getProperty("image.base.url");

    private ImageService() {

    }

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        Path path = Path.of(basePath, imagePath);

        try (imageContent) {
            Files.createDirectories(path.getParent());
            Files.write(path, imageContent.readAllBytes(), StandardOpenOption.CREATE);
        }
    }

    @SneakyThrows
    public Optional<InputStream> download(String imagePath) {
        Path path = Path.of(basePath, imagePath);

        return Files.exists(path) ?
                Optional.of(Files.newInputStream(path)) :
                Optional.empty();
    }
}
