package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.StatusDTO;
import com.pali.palindromebackend.dto.SuggestionDTO;
import com.pali.palindromebackend.dto.SuggestionUserDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
public interface StatusBO extends SuperBO {
    public void saveStatus(StatusDTO dto) throws Exception;

    public void updateStatus(StatusDTO dto) throws Exception;

    public void deleteStatus(int statusId) throws Exception;

    public List<StatusDTO> getAllStatuses() throws Exception;

    public StatusDTO getStatus(int statusId) throws Exception;
}
