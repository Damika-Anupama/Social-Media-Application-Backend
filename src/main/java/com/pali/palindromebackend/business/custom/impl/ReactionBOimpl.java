package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.ReactionBO;
import com.pali.palindromebackend.business.util.ReactionEntityDTOMapper;
import com.pali.palindromebackend.business.util.StatusEntityDTOMapper;
import com.pali.palindromebackend.dao.ReactionDAO;
import com.pali.palindromebackend.dao.StatusDAO;
import com.pali.palindromebackend.dto.ReactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 10/05/2022
 **/
@Transactional
@Service
public class ReactionBOimpl implements ReactionBO {

    @Autowired
    private ReactionDAO dao;

    @Autowired
    private ReactionEntityDTOMapper mapper;

    public ReactionBOimpl() {
    }
    @Override
    public void saveReaction(ReactionDTO dto) throws Exception {
        dao.save(mapper.getReaction(dto));
    }

    @Override
    public void updateReaction(ReactionDTO dto) throws Exception {
        dao.save(mapper.getReaction(dto));
    }

    @Override
    public void deleteReaction(int reactionId) throws Exception {
        dao.deleteById(reactionId);
    }

    @Override
    public List<ReactionDTO> getAllReactions() throws Exception {
        return dao.findAll().stream().
                map(reactions -> mapper.getReactionDTO(reactions)).collect(Collectors.toList());
    }

    @Override
    public ReactionDTO getReaction(int reactionId) throws Exception {
        return dao.findById(reactionId).map(reaction -> mapper.getReactionDTO(reaction)).get();
    }

    @Override
    public boolean checkUniqueness(int userId, int launchId) {
        return dao.findReaction(userId,launchId);
    }
}
