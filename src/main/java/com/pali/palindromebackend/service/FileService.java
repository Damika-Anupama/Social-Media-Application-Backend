package com.pali.palindromebackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 03/06/2021
 **/
@Service
public class FileService {
    @Value("${upload.path}")
    private String uploadPath;

    public String saveFile(MultipartFile file, String fileType){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-"));
        String fileName = date + file.getOriginalFilename();

        // folderPath here is /sismed/temp/exames
        String folderPath = uploadPath + fileType;
        File f1 = new File(folderPath);
        f1.mkdirs();
        String filePath = folderPath + File.separator + fileName;

        try {
            // Copies Spring's multipartfile inputStream to /sismed/temp/exames (absolute path)
//            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            byte[] bytes = file.getBytes();
            File file1 = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return filePath;
    }
}
