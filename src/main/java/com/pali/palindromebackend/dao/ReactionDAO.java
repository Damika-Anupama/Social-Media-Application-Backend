package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/

public interface ReactionDAO  extends JpaRepository<Reaction,Integer> {
}
