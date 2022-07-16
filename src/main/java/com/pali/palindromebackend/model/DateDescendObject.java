package com.pali.palindromebackend.model;

import com.pali.palindromebackend.entity.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/17/2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateDescendObject implements Serializable {
    private Date createdDate;
    private ObjectType type;
    private Object object; // This object contains launch| community|friend|page object
}
