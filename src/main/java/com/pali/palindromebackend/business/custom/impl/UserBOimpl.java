package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.UserBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.UserDAO;
import com.pali.palindromebackend.dto.UserDTO;
import com.pali.palindromebackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public String saveUser(UserDTO dto) throws Exception {
        User user = mapper.getUser(dto);
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setIsActive(true);
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        user.setLastLogin(new Date(System.currentTimeMillis()));
        userDAO.save(user);
        return "User Saved Successfully";
    }

    @Override
    public void updateUser(UserDTO dto) throws Exception {
        User user = mapper.getUser(dto);
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        userDAO.save(mapper.getUser(dto));
    }

    @Override
    public void deleteUser(int userId) throws Exception {
        userDAO.deleteById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        return userDAO.findAll().stream().
                map(user -> mapper.getUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(int userId) throws Exception {
        return userDAO.findById(userId).map(user -> mapper.getUserDTO(user)).get();
    }

    @Override
    public UserDTO getUserByName(String userName) throws Exception {
        return userDAO.findUserByUsername(userName).map(user -> mapper.getUserDTO(user)).get();
    }

    @Override
    public String getUserProfilePicture(int id) throws Exception {
        return userDAO.findUserProfilePicture(id);
    }

    @Override
    public void updateUserNormalDetails(UserDTO dto) {
        userDAO.updateUserNormalDetails(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getShortDescription(), dto.getProfilePicture(), dto.getContactNum());
    }
}
