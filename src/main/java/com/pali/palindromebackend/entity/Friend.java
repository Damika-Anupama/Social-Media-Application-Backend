package com.pali.palindromebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "friend")
public class Friend implements SuperEntity {
    @Id
    @Column(name = "friendship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int friendshipId;
    private int friendId;
    @Column(name = "friendship_date")
    private Date friendshipDate;
    private boolean isBlocked;
    private boolean isConfirmed;
    @JsonIgnoreProperties("launches")
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
