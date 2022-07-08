package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.UserDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface UserBO extends SuperBO {
    String  saveUser(UserDTO dto) throws Exception;
    void updateUser(UserDTO dto) throws Exception;
    void deleteUser(int userId) throws Exception;
    public List<UserDTO> getAllUsers() throws Exception;
    public UserDTO getUser(int userId) throws Exception;
    public UserDTO getUserByName(String userName) throws Exception;
    public String getUserProfilePicture(int id) throws Exception;

    public void updateUserNormalDetails(UserDTO dto);
}
