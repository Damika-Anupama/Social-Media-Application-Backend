package com.pali.palindromebackend.business.util;

import com.pali.palindromebackend.dao.LaunchDAO;
import com.pali.palindromebackend.dao.UserDAO;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.dto.ReactionDTO;
import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.entity.Reaction;
import com.pali.palindromebackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 10/05/2022
 **/
@Mapper(componentModel = "spring", uses = {EntityDTOMapper.class})
public abstract class ReactionEntityDTOMapper {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LaunchDAO launchDAO;

    @Mapping(source = ".", target = "user")
    public abstract Reaction getReaction(ReactionDTO dto);

    @Mapping(source = "user", target = "userId", qualifiedByName = "User")
    public abstract ReactionDTO getReactionDTO(Reaction reaction);

    public User getUser(ReactionDTO dto) {
        return userDAO.getOne(dto.getUserId());
    }

    @Named("User")
    public int getUserName(User user) {
        return user.getId();
    }

    public Launch getLaunch(ReactionDTO dto) {
        Launch one = launchDAO.getOne(dto.getLaunchId());
        System.out.println(one);
        return one;
    }

    @Named("Launch")
    public int getLaunchId(Launch launch) {
        return launch.getId();
    }
}
