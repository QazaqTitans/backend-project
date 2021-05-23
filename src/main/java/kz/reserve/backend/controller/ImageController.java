package kz.reserve.backend.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ImageController {

    @Value("${upload.folder}")
    private String uploadDir;

    @GetMapping(
            value = "/image/{name}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImage(@PathVariable String name) {
        File serverFile = new File(uploadDir + "/" + name);

        try {
            return Files.readAllBytes(serverFile.toPath());
        } catch (IOException e) {
            return null;
        }
    }
}
