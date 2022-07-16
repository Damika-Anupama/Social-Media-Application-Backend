package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.dto.FriendDTO;
import com.pali.palindromebackend.entity.Community;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/

public interface CommunityBO extends SuperBO {
    Community saveCom(CommunityDTO dto) throws Exception;

    void updateCom(CommunityDTO dto) throws Exception;

    void deleteCom(int comId) throws Exception;

    List<CommunityDTO> getAllComs() throws Exception;

    CommunityDTO getCom(int ComId) throws Exception;

}
