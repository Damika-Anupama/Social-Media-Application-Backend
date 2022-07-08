package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.SuggestionDTO;
import com.pali.palindromebackend.dto.SuggestionUserDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 19/06/2021
 **/
public interface SuggestionBO extends SuperBO {
    void saveSuggestion(SuggestionDTO dto) throws Exception;

    void updateSuggestion(SuggestionDTO dto) throws Exception;

    void deleteSuggestion(int suggestionId) throws Exception;

    List<SuggestionDTO> getAllSuggestions() throws Exception;
    List<SuggestionUserDTO> getAllSuggestionsWithUsers() throws Exception;

    SuggestionDTO getSuggestion(int suggestionId) throws Exception;
}
