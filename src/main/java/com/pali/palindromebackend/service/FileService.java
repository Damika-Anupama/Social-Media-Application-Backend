package com.pali.palindromebackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 03/06/2021
 **/
@Service
public class FileService {
    @Value("${media-upload.path}")
    private String UploadPath;

    private final String mediaUploadPath = UploadPath + "media/";

    public String saveMediaFile(MultipartFile file, String fileType) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-"));
        String fileName = date + file.getOriginalFilename();

        // folderPath here is /sismed/temp/exames
        String folderPath = mediaUploadPath + fileType;
        File f1 = new File(folderPath);
        f1.mkdirs();
        String filePath = folderPath + File.separator + fileName;

        try {
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

    public byte[] getMedia(String path) {
        try {
            Path p = Paths.get(path);
            byte[] bytes = Files.readAllBytes(p);
            if (bytes == null) {
                System.out.println("empty");
            }
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveFriend(int userId, int friendId, Date frinendshipSince) throws IOException {
        String friendSavePath = UploadPath + "friend/";
        String pathName = friendSavePath + userId + ".txt";
        File file = new File(pathName);
        System.out.println(pathName);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String saveFriend = String.valueOf(friendId) + "- " + frinendshipSince + "," +System.lineSeparator();
        byte[] bytes = saveFriend.getBytes();
        Files.write(Paths.get(pathName),bytes,StandardOpenOption.APPEND);
        return pathName;
    }
}


// to convert a byte array to a mutipartform data object
/*
class BASE64DecodedMultipartFile implements MultipartFile {
    private final byte[] imgContent;

    public BASE64DecodedMultipartFile(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }
}
*/
