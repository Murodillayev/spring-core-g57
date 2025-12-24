package uz.pdp.controller;

import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @GetMapping("/download/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        return service.download(fileName);
    }

    // http:kun.uz/file/file1.png
    // http:kun.uz/file/file2.png
    // http:kun.uz/file/file3.png
}
