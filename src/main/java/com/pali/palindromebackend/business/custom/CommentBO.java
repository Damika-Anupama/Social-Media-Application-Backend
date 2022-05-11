package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.CommentDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 5/11/2022
 **/
public interface CommentBO extends SuperBO {
    public void saveComment(CommentDTO dto) throws Exception;

    public void updateComment(CommentDTO dto) throws Exception;

    public void deleteComment(int commentId) throws Exception;

    public List<CommentDTO> getAllComments() throws Exception;

    public CommentDTO getComment(int CommentId) throws Exception;

}
