package com.pali.palindromebackend.dto;

import com.pali.palindromebackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/05/2021
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO implements Serializable {
    private int id;
    private String details;
    private String media;
    private String mediaType;
    private Date createdTime;
    private Date expiringTime;
    private boolean isExpired;
    private boolean isSponsored;
    private int viewedNumber;
    private int user;
}
