package com.pali.palindromebackend.entity.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 13/06/2021
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaunchUserDetails implements Serializable {
    private int id;
    private String media;
    private String mediaType;
    private String description;
    private String feeling;
    private int userId;
    private String userName;
    private String shortDescription;
    private String profilePicture;
}
