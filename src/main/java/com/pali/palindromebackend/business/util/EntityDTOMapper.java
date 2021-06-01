package com.pali.palindromebackend.business.util;

import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.dto.UserDTO;
import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.entity.User;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
    User getUser(UserDTO dto);
    UserDTO getUserDTO(User user);

    Launch getLaunch(LaunchDTO dto);
    LaunchDTO getLaunchDTO(Launch launch);
}
