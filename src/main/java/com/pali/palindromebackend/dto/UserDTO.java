package com.pali.palindromebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@AllArgsConstructor@NoArgsConstructor@Data
public class UserDTO implements Serializable {
    private int id;
    @NotEmpty(message = "username cannot be empty !!")
    private String name;
    @NotNull(message = "password cannot be null !!")
    private String password;
}
