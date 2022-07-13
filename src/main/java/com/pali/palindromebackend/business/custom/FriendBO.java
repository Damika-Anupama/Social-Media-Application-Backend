package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.FriendDTO;
import com.pali.palindromebackend.entity.Friend;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
public interface FriendBO extends SuperBO {
    void saveFriend(FriendDTO dto) throws Exception;

    void updateFriend(FriendDTO dto) throws Exception;

    void deleteFriend(int friendshipId) throws Exception;

    List<FriendDTO> getAllFriends() throws Exception;

    FriendDTO getFriend(int friendshipId) throws Exception;
}
