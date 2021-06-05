package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.UserDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface UserBO extends SuperBO {
    public void saveUser(UserDTO dto) throws Exception;
    public void updateUser(UserDTO dto) throws Exception;
    public void deleteUser(Integer userId) throws Exception;
    public List<UserDTO> getAllUsers() throws Exception;
    public UserDTO getUser(Object userName) throws Exception;
}
