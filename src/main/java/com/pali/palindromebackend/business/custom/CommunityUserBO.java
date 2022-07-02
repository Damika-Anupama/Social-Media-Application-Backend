package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.dto.CommunityUserDTO;
import com.pali.palindromebackend.entity.CommunityUserPK;

import java.util.List;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/22/2022
 **/

public interface CommunityUserBO {
    public void saveCommunityUser(CommunityUserDTO dto) throws Exception;

    public void updateCommunityUser(CommunityUserDTO dto) throws Exception;

    public void deleteCommunityUser(CommunityUserPK pk) throws Exception;

    public List<CommunityUserDTO> getAllCommunityUsers() throws Exception;

    public CommunityUserDTO getCommunityUser(CommunityUserPK pk) throws Exception;
}
