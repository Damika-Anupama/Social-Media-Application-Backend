package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.dto.FriendDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/

public interface CommunityBO extends SuperBO {
    public void saveCom(CommunityDTO dto) throws Exception;

    public void updateCom(CommunityDTO dto) throws Exception;

    public void deleteCom(int comId) throws Exception;

    public List<CommunityDTO> getAllComs() throws Exception;

    public CommunityDTO getCom(int ComId) throws Exception;
}
