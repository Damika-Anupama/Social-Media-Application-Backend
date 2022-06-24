package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.CommunityUser;
import com.pali.palindromebackend.entity.CommunityUserPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/23/2022
 **/
public interface CommunityUserDAO extends JpaRepository<CommunityUser, CommunityUserPK> {
}
