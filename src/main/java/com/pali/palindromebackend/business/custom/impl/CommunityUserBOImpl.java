package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.CommunityUserBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.CommunityDAO;
import com.pali.palindromebackend.dto.CommunityUserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/22/2022
 **/
public class CommunityUserBOImpl implements CommunityUserBO {
    @Autowired
    private CommunityDAO dao;
    @Autowired
    private EntityDTOMapper mapper;
    @Override
    public void saveCommunityUser(CommunityUserDTO dto) throws Exception {
        dao.save(mapper.getCommunityUser(dto));
    }

    @Override
    public void updateCommunityUser(CommunityUserDTO dto) throws Exception {

    }

    @Override
    public void deleteCommunityUser(int comId, int userId) throws Exception {

    }

    @Override
    public List<CommunityUserDTO> getAllCommunityUsers() throws Exception {
        return null;
    }

    @Override
    public CommunityUserDTO getCommunityUser(int comId, int userId) throws Exception {
        return null;
    }
}
