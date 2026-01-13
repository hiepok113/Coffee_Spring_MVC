package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;


@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadFile(MultipartFile avatarFile , String targetFolder) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        String finalName = "";
        if(avatarFile.isEmpty()) {
            return finalName;
        }
        try {
            byte[] bytes = avatarFile.getBytes();

            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();

            finalName =System.currentTimeMillis() + "-" + avatarFile.getOriginalFilename();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator +finalName);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
             } catch (IOException e) {
                e.printStackTrace();
            }
        return finalName;
    }

}
