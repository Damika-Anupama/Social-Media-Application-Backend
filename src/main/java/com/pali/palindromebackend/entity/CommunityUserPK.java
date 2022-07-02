package com.pali.palindromebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class CommunityUserPK implements Serializable {
    @ManyToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> userId;

    @ManyToMany
    @JoinColumn(name = "community_id", referencedColumnName = "community_id")
    private List<Community> communityId;
}
