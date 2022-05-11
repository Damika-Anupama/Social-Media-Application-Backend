package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.ShareBO;
import com.pali.palindromebackend.business.util.LaunchEntityDTOMapper;
import com.pali.palindromebackend.dao.ShareDAO;
import com.pali.palindromebackend.dto.ShareDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 5/11/2022
 **/
@Transactional
@Service
public class ShareBOimpl implements ShareBO {

    @Autowired
    private ShareDAO dao;
    private LaunchEntityDTOMapper mapper;

    public ShareBOimpl() {
    }

    @Override
    public void saveShare(ShareDTO dto) throws Exception {
        dao.save(mapper.getShare(dto));
    }

    @Override
    public void updateShare(ShareDTO dto) throws Exception {
        dao.save(mapper.getShare(dto));
    }

    @Override
    public void deleteShare(int shareId) throws Exception {
        dao.deleteById(shareId);
    }

    @Override
    public List<ShareDTO> getAllShares() throws Exception {
        return dao.findAll().stream().
                map(share -> mapper.getShareDTO(share)).collect(Collectors.toList());
    }

    @Override
    public ShareDTO getShare(int shareId) throws Exception {
        return dao.findById(shareId).map(share -> mapper.getShareDTO(share)).get();
    }
}
