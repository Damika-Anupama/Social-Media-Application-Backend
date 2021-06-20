package com.pali.palindromebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 18/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuggestionDTO implements Serializable {
    private int id;
    private String message;
    private String priority;
    private String feeling;
    private int user;
}
