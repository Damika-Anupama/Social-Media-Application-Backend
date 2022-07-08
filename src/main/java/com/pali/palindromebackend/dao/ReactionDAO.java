package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/

public interface ReactionDAO  extends JpaRepository<Reaction,Integer> {
    @Query("select r from Reaction r where r.user.id like ?1 AND r.launch like ?2")
    boolean findReaction(int userId, int launchId);
}
