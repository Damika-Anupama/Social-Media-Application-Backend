package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.CommunityLaunchBO;
import com.pali.palindromebackend.business.util.CommunityLaunchEntityDTOMapper;
import com.pali.palindromebackend.dao.CommunityLaunchDAO;
import com.pali.palindromebackend.dto.CommunityLaunchDTO;
import com.pali.palindromebackend.entity.CommunityLaunchPK;
import com.pali.palindromebackend.model.CommunityLaunchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/17/2022
 **/
@Transactional
@Service
public class CommunityLaunchBOImpl implements CommunityLaunchBO {
    @Autowired
    private CommunityLaunchDAO dao;
    @Autowired
    private CommunityLaunchEntityDTOMapper mapper;

    @Override
    public void save(CommunityLaunchDTO dto) {

    }

    @Override
    public void update(CommunityLaunchDTO dto) {

    }

    @Override
    public void delete(CommunityLaunchPK pk) {

    }

    @Override
    public List<CommunityLaunchDTO> getAll() {
        return null;
    }

    @Override
    public CommunityLaunchDTO get(CommunityLaunchPK pk) {
        return null;
    }

    @Override
    public List<CommunityLaunchDetail> getCommunityLaunches(int comId) {
        return dao.findAllByCommunityId(comId);
    }
}
