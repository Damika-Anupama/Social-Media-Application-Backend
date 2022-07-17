package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.entity.custom.LaunchUserDetails;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
public interface LaunchBO extends SuperBO {
    Launch saveLaunch(LaunchDTO dto) throws Exception;

    void updateLaunch(LaunchDTO dto) throws Exception;

    void deleteLaunch(int launchId) throws Exception;

    LaunchDTO getLaunch(int launchId) throws Exception;

    List<LaunchDTO> getAllLaunches() throws Exception;

    List<LaunchDTO> getLaunchesByUserId(int userId) throws Exception;

    List<LaunchUserDetails> getAllLaunchesWithUserDetails() throws Exception;
}
