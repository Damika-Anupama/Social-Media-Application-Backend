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
        friend.setFriendId(dto.getFriendId());
        friend.setFriendshipDate(dto.getFriendshipDate());
        friend.setFriendshipId(dto.getFriendshipId());
        friend.setIsBlocked(dto.isBlocked());
        return friend;
    }

    @Override
    public FriendDTO getFriendDTO(Friend friend) {
        if (friend == null) {
            return null;
        }

        FriendDTO dto = new FriendDTO();

        dto.setUser(getUserName(friend.getUser()));
        dto.setFriendId(friend.getFriendId());
        dto.setBlocked(friend.getIsBlocked());
        dto.setFriendshipDate(friend.getFriendshipDate());
        dto.setFriendshipId(friend.getFriendshipId());
        return dto;
    }
}
