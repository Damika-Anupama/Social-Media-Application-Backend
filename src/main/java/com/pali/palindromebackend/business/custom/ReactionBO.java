package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.ReactionDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface ReactionBO extends SuperBO {
    public void saveReaction(ReactionDTO dto) throws Exception;

    public void updateReaction(ReactionDTO dto) throws Exception;

    public void deleteReaction(int reactionId) throws Exception;

    public List<ReactionDTO> getAllReactions() throws Exception;

    public ReactionDTO getReaction(int reactionId) throws Exception;
}
