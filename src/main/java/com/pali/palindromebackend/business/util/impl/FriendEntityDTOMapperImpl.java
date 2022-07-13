package com.pali.palindromebackend.business.util.impl;

import com.pali.palindromebackend.business.util.FriendEntityDTOMapper;
import com.pali.palindromebackend.dto.FriendDTO;
import com.pali.palindromebackend.entity.Friend;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
public class FriendEntityDTOMapperImpl extends FriendEntityDTOMapper {
    @Override
    public Friend getFriend(FriendDTO dto) {
        if (dto == null) {
            return null;
        }

        Friend friend = new Friend();
        try {
            friend.setUser(getUser(dto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        friend.setFriend1(dto.getFriend1());
        friend.setAskedDate(dto.getAskedDate());
        friend.setIsConfirmed(dto.getIsConfirmed());
        friend.setFriendshipDate(dto.getFriendshipDate());
        friend.setIsBlocked(dto.getIsBlocked());
        friend.setBlockedBy(dto.getBlockedBy());
        friend.setBlockedDate(dto.getBlockedDate());
        return friend;
    }

    @Override
    public FriendDTO getFriendDTO(Friend friend) {
        if (friend == null) {
            return null;
        }
        return new FriendDTO(
                friend.getFriendshipId(),
                friend.getFriend1(),
                friend.getUser().getId(),
                friend.getAskedDate(),
                friend.getIsConfirmed(),
                friend.getFriendshipDate(),
                friend.getIsBlocked(),
                friend.getBlockedBy(),
                friend.getBlockedDate()
                );
    }
}
