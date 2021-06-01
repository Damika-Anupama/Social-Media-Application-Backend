package com.pali.palindromebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LaunchDTO implements Serializable {
    private String id;
    private String media;
    private String description;
    private String  feeling;
}
