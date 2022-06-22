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
    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "user_community",
            joinColumns = @JoinColumn(name = "community_id", referencedColumnName = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "launch_community",
            joinColumns = @JoinColumn(name = "community_id", referencedColumnName = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "launch_id", referencedColumnName = "id"))
    private List<Launch> launches;

    public Community(int communityId, String title, String description, Date createdDate, String groupIcon, String wallpaper) {
        this.communityId = communityId;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.groupIcon = groupIcon;
        this.wallpaper = wallpaper;
    }
}
