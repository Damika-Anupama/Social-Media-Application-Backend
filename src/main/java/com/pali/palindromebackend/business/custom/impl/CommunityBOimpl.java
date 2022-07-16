package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.CommunityBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.CommunityDAO;
import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.entity.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/
@Transactional
@Service
public class CommunityBOimpl implements CommunityBO {

    @Autowired
    private CommunityDAO dao;
    @Autowired
    private EntityDTOMapper mapper;

    @Override
    public Community saveCom(CommunityDTO dto) throws Exception {
        return dao.save(mapper.getCommunity(dto));
    }

    @Override
    public void updateCom(CommunityDTO dto) throws Exception {
        dao.save(mapper.getCommunity(dto));
    }

    @Override
    public void deleteCom(int comId) throws Exception {
        dao.deleteById(comId);
    }

    @Override
    public List<CommunityDTO> getAllComs() throws Exception {
        return dao.findAll().stream().
                map(com -> mapper.getCommunityDTO(com)).collect(Collectors.toList());
    }

    @Override
    public CommunityDTO getCom(int comId) throws Exception {
        return dao.findById(comId).map(com -> mapper.getCommunityDTO(com)).get();
    }


}
