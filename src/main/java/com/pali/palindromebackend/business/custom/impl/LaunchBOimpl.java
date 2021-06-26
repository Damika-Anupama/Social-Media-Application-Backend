package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.LaunchBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.business.util.LaunchEntityDTOMapper;
import com.pali.palindromebackend.dao.LaunchDAO;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.entity.custom.LaunchUserDetails;
import com.pali.palindromebackend.model.DashboardLaunchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
@Transactional
@Service
public class LaunchBOimpl implements LaunchBO {
    @Autowired
    private LaunchDAO dao;

    @Autowired
    private LaunchEntityDTOMapper mapper;

    public LaunchBOimpl() {
    }

    @Override
    public void saveLaunch(LaunchDTO dto) throws Exception {
        dao.save(mapper.getLaunch(dto));
    }

    @Override
    public void updateLaunch(LaunchDTO dto) throws Exception {
        dao.save(mapper.getLaunch(dto));
    }

    @Override
    public void deleteLaunch(int launchId) throws Exception {
        dao.deleteById(launchId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LaunchDTO> getAllLaunches() throws Exception {
        return dao.findAll().stream().
                map(launch -> mapper.getLaunchDTO(launch)).collect(Collectors.toList());
    }

    @Override
    public LaunchDTO getLaunch(int launchId) throws Exception {
        return dao.findById(launchId).map(launch -> mapper.getLaunchDTO(launch)).get();
    }

    @Override
    public List<LaunchDTO> getLaunchesByUserId(int userId) throws Exception {
        return dao.findLaunchesByUserId(userId).stream().
                map(launch -> mapper.getLaunchDTO(launch)).collect(Collectors.toList());
    }

    @Override
    public List<LaunchUserDetails> getAllLaunchesWithUserDetails() throws Exception {
        Collection<LaunchUserDetails> launchesWithUserDetails = dao.findLaunchesWithUserDetails();
        return new ArrayList<LaunchUserDetails>(dao.findLaunchesWithUserDetails());
    }
}
