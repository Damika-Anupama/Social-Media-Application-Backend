package com.pali.palindromebackend.dto;

import com.pali.palindromebackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@AllArgsConstructor@NoArgsConstructor@Data
public class UserDTO implements Serializable {
    @NotEmpty(message = "username cannot be empty !!")
    private String username;
    private Date createdAt;
    private int id;
    private boolean isActive;
    @NotNull(message = "password cannot be null !!")
    private String password;
    private Role role;
    private Date updatedAt;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
