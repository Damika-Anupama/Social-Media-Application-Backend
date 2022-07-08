package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.ReactionDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface ReactionBO extends SuperBO {
    void saveReaction(ReactionDTO dto) throws Exception;

    void updateReaction(ReactionDTO dto) throws Exception;

    void deleteReaction(int reactionId) throws Exception;

    List<ReactionDTO> getAllReactions() throws Exception;

    ReactionDTO getReaction(int reactionId) throws Exception;

    boolean checkUniqueness(int userId, int launchId);
}
