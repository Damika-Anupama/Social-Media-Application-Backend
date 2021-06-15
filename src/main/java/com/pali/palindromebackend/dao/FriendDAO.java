package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
public interface FriendDAO extends JpaRepository<Friend, Integer> {

}
