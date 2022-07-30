package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.UserDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface UserBO extends SuperBO {
    String saveUser(UserDTO dto) throws Exception;

    void updateUser(UserDTO dto) throws Exception;

    void deleteUser(int userId) throws Exception;

    List<UserDTO> getAllUsers() throws Exception;

    UserDTO getUser(int userId) throws Exception;

    UserDTO getUserByName(String userName) throws Exception;

    UserDTO getUserByEmail(String email);

    String getUserProfilePicture(int id) throws Exception;

    void updateUserNormalDetails(UserDTO dto);
}
