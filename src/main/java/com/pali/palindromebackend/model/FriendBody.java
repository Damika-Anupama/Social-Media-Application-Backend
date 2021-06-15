package com.pali.palindromebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendBody implements Serializable {
    private int userId;
    private int friendId;
    @UpdateTimestamp
    private Date friendshipSince;
}
