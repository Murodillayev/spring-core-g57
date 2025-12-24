package uz.pdp.service;

import org.postgresql.util.internal.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileService {
    private final String root = "/Users/macbookpro/Documents/pdp/spring-core-g57/src/main/resources/store";

    public String uploadUserImg(MultipartFile img) {
        try {
            String fileExtension = getFileExtension(img.getOriginalFilename());
            String fileName = (UUID.randomUUID() + "." + fileExtension).replace("-", "");
            Path path = Paths.get(root, fileName);
            Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "http://localhost:8080/file/download/"  + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return ""; // Kengaytma topilmadi
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public ResponseEntity<Resource> download(String fileName) {
        Path path = Paths.get(root, fileName);
        FileSystemResource resource = new FileSystemResource(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .header("Content-Type", "application/octet-stream")
                .body(resource);
    }
}
