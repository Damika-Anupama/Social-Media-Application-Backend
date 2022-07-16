package com.pali.palindromebackend.model;

import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.dto.FriendDTO;
import com.pali.palindromebackend.entity.Gender;
import com.pali.palindromebackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/15/2022
 **/
@AllArgsConstructor
@NoArgsConstructor@Data
// This is the model class that required for the profile page for each user
public class UserProfileBody implements Serializable {
    // implicitly setting data
    private int id;
    private Date createdAt;
    private boolean isActive;
    private Role role;
    private Date updatedAt;
    private Date lastLogin;
    private boolean onlineStatus;
    //explicitly setting data
    @NotEmpty(message = "username cannot be empty !!")
    private String username;
    private Gender gender;
    private String email;
    private String shortDescription;
    private byte[] profilePicture;
    private String contactNum;
    private Date joinedDate;

    //other details of the user
    private int noOfCommunities;
    private int noOfFriends;

    // details about the launches shared
    private List<DashboardLaunchDetail> launches;
    // community names with date + id
    private List<ResponseCommunityBody> communities;
    // friends with details
    private List<FriendDTO> friends;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
