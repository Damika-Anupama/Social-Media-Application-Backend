package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.ShareDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 5/11/2022
 **/
public interface ShareBO extends SuperBO {
    public void saveShare(ShareDTO dto) throws Exception;

    public void updateShare(ShareDTO dto) throws Exception;

    public void deleteShare(int shareId) throws Exception;

    public List<ShareDTO> getAllShares() throws Exception;

    public ShareDTO getShare(int shareId) throws Exception;

}
