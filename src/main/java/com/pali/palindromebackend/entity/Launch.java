package com.pali.palindromebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String media;
    @Column(name = "media_type")
    private String mediaType;
    private String description;
    private String feeling;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonIgnoreProperties("launches")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties("launch")
    @OneToMany(mappedBy = "launch")
    private List<Reaction> reactions;

}
