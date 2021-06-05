package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.UserBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.UserDAO;
import com.pali.palindromebackend.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@Transactional
@Service
public class UserBOimpl implements UserBO {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EntityDTOMapper mapper;

    public UserBOimpl() {
    }

    @Override
    public void saveUser(UserDTO dto) throws Exception {
        userDAO.save(mapper.getUser(dto));
    }

    @Override
    public void updateUser(UserDTO dto) throws Exception {
        userDAO.save(mapper.getUser(dto));
    }

    @Override
    public void deleteUser(Integer  userId) throws Exception {
        userDAO.deleteById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        return userDAO.findAll().stream().
                map(user -> mapper.getUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(Object userId) throws Exception {
        return userDAO.findById(userId).map(user -> mapper.getUserDTO(user)).get();
    }
}
