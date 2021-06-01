package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.LaunchBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.LaunchDAO;
import com.pali.palindromebackend.dto.LaunchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private EntityDTOMapper mapper;

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
    public void deleteLaunch(String launchId) throws Exception {
        dao.deleteById(launchId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LaunchDTO> getAllLaunches() throws Exception {
        return dao.findAll().stream().
                map(launch -> mapper.getLaunchDTO(launch)).collect(Collectors.toList());
    }

    @Override
    public LaunchDTO getLaunch(String launchId) throws Exception {
        return dao.findById(launchId).map(launch -> mapper.getLaunchDTO(launch)).get();
    }
}
