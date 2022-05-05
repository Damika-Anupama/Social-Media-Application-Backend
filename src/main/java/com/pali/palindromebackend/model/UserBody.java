package com.pali.palindromebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 19/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBody implements Serializable {
    private int id;
    private String username;
    private String email;
    private String shortDes;
    private MultipartFile profilePic;
    private String phoneNum;
    private Date lastLogin;
}
