package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.CommunityUser;
import com.pali.palindromebackend.entity.CommunityUserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/23/2022
 **/
public interface CommunityUserDAO extends JpaRepository<CommunityUser, CommunityUserPK> {
    @Query("select new com.pali.palindromebackend.entity.CommunityUser (" +
            "cu.pk,cu.joinedDate,cu.updatedDate,cu.role" +
            ") from CommunityUser cu where cu.pk.userId.id like ?1")
    ArrayList<CommunityUser> findAllByUserId(int userId);

    @Query("select new com.pali.palindromebackend.entity.CommunityUser (" +
            "cu.pk,cu.joinedDate,cu.updatedDate,cu.role" +
            ") from CommunityUser cu where cu.pk.communityId.communityId like ?1")
    ArrayList<CommunityUser> findAllByCommunityId(int comId);
}
