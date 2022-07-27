package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.dto.CommunityUserDTO;
import com.pali.palindromebackend.entity.CommunityUser;
import com.pali.palindromebackend.entity.CommunityUserPK;
import com.pali.palindromebackend.model.MiniUserComDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 6/22/2022
 **/

public interface CommunityUserBO {
    void saveCommunityUser(CommunityUserDTO dto) throws Exception;

    void updateCommunityUser(CommunityUserDTO dto) throws Exception;

    void deleteCommunityUser(CommunityUserPK pk) throws Exception;

    List<CommunityUserDTO> getAllCommunityUsers() throws Exception;

    CommunityUserDTO getCommunityUser(CommunityUserPK pk) throws Exception;

    List<CommunityUserDTO> getAllCommunitiesByUserId(int userId);

    List<MiniUserComDTO> getCommunityUsers(int comId);
}
