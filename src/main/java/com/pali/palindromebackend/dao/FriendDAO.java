package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
public interface FriendDAO extends JpaRepository<Friend, Integer> {

    @Query("select f from friend f where " +
            "f.friend1 like ?1 and f.user.id like ?2")
    Friend findFriendship(int friend1, int friend2);
    @Query("select new com.pali.palindromebackend.entity.Friend (" +
            "f.friendshipId,f.friend1,f.user,f.askedDate,f.isConfirmed,f.friendshipDate,f.isBlocked,f.blockedBy,f.blockedDate" +
            ") from friend f where f.friend1 like ?1")
    List<Friend> findAllfriend1(int userId);
    @Query("select new com.pali.palindromebackend.entity.Friend (" +
            "f.friendshipId,f.friend1,f.user,f.askedDate,f.isConfirmed,f.friendshipDate,f.isBlocked,f.blockedBy,f.blockedDate" +
            ") from friend f where f.user.id like ?1")
    List<Friend> findAllfriend2(int userId);
}
