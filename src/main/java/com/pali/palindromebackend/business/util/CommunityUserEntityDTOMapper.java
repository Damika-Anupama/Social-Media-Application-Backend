package com.pali.palindromebackend.business.util;

import com.pali.palindromebackend.dto.CommunityUserDTO;
import com.pali.palindromebackend.dto.FriendDTO;
import com.pali.palindromebackend.entity.CommunityUser;
import com.pali.palindromebackend.entity.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/23/2022
 **/
@Mapper(componentModel = "spring", uses = {EntityDTOMapper.class})
public abstract class CommunityUserEntityDTOMapper {
//
//    @Mapping(source = ".", target = "user")
//    public abstract CommunityUser getCommunityUser(CommunityUserDTO dto);
//
//    @Mapping(source = "user", target = "user", qualifiedByName = "User")
//    public abstract CommunityUserDTO getCommunityUserDTO(CommunityUser communityUser);
}
