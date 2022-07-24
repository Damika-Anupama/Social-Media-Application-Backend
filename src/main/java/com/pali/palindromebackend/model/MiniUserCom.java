package com.pali.palindromebackend.model;

import com.pali.palindromebackend.entity.OnlineStatus;
import com.pali.palindromebackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/18/2022
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MiniUserCom implements Serializable {
    private int userId;
    private String username;
    private String shortDescription;
    private byte[] profilePicture;
    private Role role;
    private OnlineStatus onlineStatus;
}
