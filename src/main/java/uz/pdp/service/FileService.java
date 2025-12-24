package uz.pdp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileService {
    private final String root = "/Users/macbookpro/Documents/pdp/spring-core-g57/src/main/resources/store";

    public String uploadUserImg(MultipartFile img) {

        try {
            Path path = Paths.get(root, img.getOriginalFilename());
            Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//            Files.write(path, img.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
