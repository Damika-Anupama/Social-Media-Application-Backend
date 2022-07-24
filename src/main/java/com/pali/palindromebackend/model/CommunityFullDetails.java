package com.pali.palindromebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/20/2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommunityFullDetails {
    private int communityId;
    private String title;
    private String description;
    private Date createdDate;
    private byte[] groupIcon;
    private byte[] wallpaper;
    private MiniUserCom miniUserCom;
    private DashboardLaunchDetail dashboardLaunchDetail;
}
