package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.CommunityLaunch;
import com.pali.palindromebackend.entity.CommunityLaunchPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/17/2022
 **/
public interface CommunityLaunchDAO extends JpaRepository<CommunityLaunch, CommunityLaunchPK> {
}
