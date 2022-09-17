package com.Intuittaxi.com.Intuittaxi.service.utils;

import com.Intuittaxi.com.Intuittaxi.service.KeyGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FileStorageUtil {
    @Autowired
    KeyGeneratorService keyGeneratorService;
    public String uploadFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("Files-Upload");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = keyGeneratorService.randomString(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return fileCode;
    }

    public Resource fetchFile(String fileCode) throws IOException {
        final Path dirPath = Paths.get("Files-Upload");
        AtomicReference<Path> foundFile = new AtomicReference<>();

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile.set(file);
                return;
            }
        });

        if (foundFile.get() != null) {
            return new UrlResource(foundFile.get().toUri());
        }

        return null;
    }
}
