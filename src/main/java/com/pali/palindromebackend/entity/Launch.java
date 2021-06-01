package com.pali.palindromebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "launch")
public class Launch implements SuperEntity{
    @Id
    private String id;
    private String media;
    private String description;
    private String feeling;
}
