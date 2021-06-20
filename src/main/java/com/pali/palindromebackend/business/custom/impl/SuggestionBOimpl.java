package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.SuggestionBO;
import com.pali.palindromebackend.business.util.SuggestionEntityDTOMapper;
import com.pali.palindromebackend.dao.SuggestionDAO;
import com.pali.palindromebackend.dto.SuggestionDTO;
import com.pali.palindromebackend.dto.SuggestionUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 19/06/2021
 **/
@Transactional
@Service
public class SuggestionBOimpl implements SuggestionBO {
    @Autowired
    private SuggestionDAO dao;

    @Autowired
    private SuggestionEntityDTOMapper mapper;

    public SuggestionBOimpl() {
    }

    @Override
    public void saveSuggestion(SuggestionDTO dto) throws Exception {
        dao.save(mapper.getSuggestion(dto));
    }

    @Override
    public void updateSuggestion(SuggestionDTO dto) throws Exception {
        dao.save(mapper.getSuggestion(dto));
    }

    @Override
    public void deleteSuggestion(int suggestionId) throws Exception {
        dao.deleteById(suggestionId);
    }

    @Override
    public List<SuggestionDTO> getAllSuggestions() throws Exception {
        return dao.findAll().stream().
                map(suggestion -> mapper.getSuggestionDTO(suggestion)).collect(Collectors.toList());
    }

    @Override
    public List<SuggestionUserDTO> getAllSuggestionsWithUsers() throws Exception {
        return dao.findAllWithUsers().stream().
                map(suggestion -> mapper.getSuggestionUserDTO(suggestion)).collect(Collectors.toList());
    }

    @Override
    public SuggestionDTO getSuggestion(int suggestionId) throws Exception {
        return dao.findById(suggestionId).map(launch -> mapper.getSuggestionDTO(launch)).get();
    }
}
