package com.pali.palindromebackend.business.custom.impl;


import com.pali.palindromebackend.business.custom.StatusBO;
import com.pali.palindromebackend.business.util.LaunchEntityDTOMapper;
import com.pali.palindromebackend.business.util.StatusEntityDTOMapper;
import com.pali.palindromebackend.dao.LaunchDAO;
import com.pali.palindromebackend.dao.StatusDAO;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.dto.StatusDTO;
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
public class StatusBOimpl implements StatusBO {

    @Autowired
    private StatusDAO dao;

    @Autowired
    private StatusEntityDTOMapper mapper;

    public StatusBOimpl() {
    }


    @Override
    public void saveStatus(StatusDTO dto) throws Exception {
        dao.save(mapper.getStatus(dto));
    }

    @Override
    public void updateStatus(StatusDTO dto) throws Exception {
        dao.save(mapper.getStatus(dto));
    }

    @Override
    public void deleteStatus(int statusId) throws Exception {
        dao.deleteById(statusId);
    }

    @Override
    public List<StatusDTO> getAllStatuses() throws Exception {
        return dao.findAll().stream().
                map(statuses -> mapper.getStatusDTO(statuses)).collect(Collectors.toList());
    }

    @Override
    public StatusDTO getStatus(int statusId) throws Exception {
        return dao.findById(statusId).map(status -> mapper.getStatusDTO(status)).get();
    }
}
