package com.pali.palindromebackend.business.util;

import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.entity.Launch;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 10/06/2021
 **/
public abstract class LaunchEntityDTOMapper {

    public abstract Launch getLaunch(LaunchDTO dto);
    public abstract LaunchDTO getLaunchDTO(Launch launch);
}
