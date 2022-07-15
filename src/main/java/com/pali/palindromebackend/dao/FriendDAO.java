package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
public interface FriendDAO extends JpaRepository<Friend, Integer> {

    @Query("select f from friend f where " +
            "f.friend1 like ?1 and f.user.id like ?2")
    Friend findFriendship(int friend1, int friend2);
}
