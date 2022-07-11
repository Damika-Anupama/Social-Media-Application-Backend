package com.pali.palindromebackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "community")
public class Community implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private int communityId;
    private String title;
    private String description;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "group_icon")
    private String groupIcon;
    private String wallpaper;
}
