package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.LaunchDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
public interface LaunchBO extends SuperBO {
    public void saveLaunch(LaunchDTO dto) throws Exception;

    public void updateLaunch(LaunchDTO dto) throws Exception;

    public void deleteLaunch(int launchId) throws Exception;

    public List<LaunchDTO> getAllLaunches() throws Exception;

    public LaunchDTO getLaunch(int launchId) throws Exception;

    public List<LaunchDTO> getLaunchesByUserId(int userId) throws Exception;
}
