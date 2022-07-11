package com.pali.palindromebackend.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
class ConvertToMultipartFile implements MultipartFile {
    private byte[] fileBytes;
    String name;
    String originalFilename;
    String contentType;
    boolean isEmpty;
    long size;
    public ConvertToMultipartFile(byte[] fileBytes, String name, String originalFilename, String contentType,
                                  long size) {
        this.fileBytes = fileBytes;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.isEmpty = false;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }
    @Override
    public String getContentType() {
        return contentType;
    }
    @Override
    public boolean isEmpty() {
        return isEmpty;
    }
    @Override
    public long getSize() {
        return size;
    }
    @Override
    public byte[] getBytes() throws IOException {
        return fileBytes;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileBytes);
    }
    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(fileBytes);
    }
}
public class FileServiceTest {
    @Value("${media-upload.path}")
    private String UploadPath;

    @Test
    public void saveCommunityGroupIcon() {
        MultipartFile groupIcon = null;
        File input_file = null;
        // width of the image
        int width = 963;

        // height of the image
        int height = 640;
        BufferedImage image = null;

        // READ IMAGE
        try {
            input_file = new File(
                    "C:\\Users\\ASUS\\Pictures\\avatar-male.png");

            // image file path create an object of
            // BufferedImage type and pass as parameter the
            // width,  height and image int
            // type. TYPE_INT_ARGB means that we are
            // representing the Alpha , Red, Green and Blue
            // component of the image pixel using 8 bit
            // integer value.

            image = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            image = ImageIO.read(input_file);

            //converting the buffered image into multipart file
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "jpg", baos );
            baos.flush();

            byte[] imageByte = baos.toByteArray();
            // Will  byte[]  Convert to  MultipartFile
            groupIcon = new ConvertToMultipartFile(imageByte, "newNamepic", "pic1", "jpg", imageByte.length);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }



        // TODO: 6/24/2022 settle the UploadPath (this was null - no reason)
//        String mediaUploadPath = UploadPath + "community/groupIcon/";
        String mediaUploadPath = "/home/damika/Desktop/pali-data/community/groupIcon/";
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-"));
        String fileName = date + input_file.getName();

        File f1 = new File(mediaUploadPath);
        f1.mkdirs();
        String filePath = mediaUploadPath + fileName;

        try {
            byte[] bytes = groupIcon.getBytes();
            File file1 = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(bytes);
            fos.close();
            System.out.println(filePath);
        } catch (IOException e) {
            System.out.println("Error when you saving your community groupIcon file try again!");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("null");
        }

    }

    @Test
    public void saveCommunityWallpaper() {
    }

    @org.junit.jupiter.api.Test
    void testSaveCommunityWallpaper() {

    }
}
