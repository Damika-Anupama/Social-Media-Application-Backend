package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.CommunityUserBO;
import com.pali.palindromebackend.business.util.CommunityUserEntityDTOMapper;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.CommunityUserDAO;
import com.pali.palindromebackend.dto.CommunityUserDTO;
import com.pali.palindromebackend.entity.CommunityUser;
import com.pali.palindromebackend.entity.CommunityUserPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/22/2022
 **/
@Transactional
@Service
public class CommunityUserBOImpl implements CommunityUserBO {
    @Autowired
    private CommunityUserDAO dao;
    @Autowired
    private CommunityUserEntityDTOMapper  mapper;

    @Override
    public void saveCommunityUser(CommunityUserDTO dto) throws Exception {
        dao.save(mapper.getCommunityUser(dto));
    }

    @Override
    public void updateCommunityUser(CommunityUserDTO dto) throws Exception {
        dao.save(mapper.getCommunityUser(dto));
    }

    @Override
    public void deleteCommunityUser(CommunityUserPK pk) throws Exception {
        dao.deleteById(pk);
    }

    @Override
    public List<CommunityUserDTO> getAllCommunityUsers() throws Exception {
        return dao.findAll().stream().map(cu -> mapper.getCommunityUserDTO(cu)).collect(Collectors.toList());
    }

    @Override
    public CommunityUserDTO getCommunityUser(CommunityUserPK pk) throws Exception {
        return dao.findById(pk).map(cu -> mapper.getCommunityUserDTO(cu)).get();
    }

    @Override
    public List<CommunityUserDTO> getAllCommunitiesByUserId(int userId) {
        return dao.findAllByUserId(userId).stream().map(cu -> mapper.getCommunityUserDTO(cu)).collect(Collectors.toList());
    }
}
