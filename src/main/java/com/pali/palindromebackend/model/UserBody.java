package com.pali.palindromebackend.model;

import com.pali.palindromebackend.entity.Gender;
import com.pali.palindromebackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private int userId;
    private String username;
    private String password;
    private String email;
    private Gender gender;
    private Date createdAt;
    private Role role;
    private boolean isActive = true;
    private String shortDes;
    private String phoneNum;
    private MultipartFile profilePic;
}
