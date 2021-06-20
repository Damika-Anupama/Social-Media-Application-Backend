package com.pali.palindromebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 18/06/2021
 **/
@AllArgsConstructor@NoArgsConstructor@Data
public class SendingUserBody implements Serializable {
    private String username;
    private String password;
    private String email;
    private String shortDescription;
    private String profilePicture;

}
