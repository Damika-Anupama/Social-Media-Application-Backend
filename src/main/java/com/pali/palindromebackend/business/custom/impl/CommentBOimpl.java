package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.CommentBO;
import com.pali.palindromebackend.business.util.LaunchEntityDTOMapper;
import com.pali.palindromebackend.dao.CommentDAO;
import com.pali.palindromebackend.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 5/11/2022
 **/
@Transactional
@Service
public class CommentBOimpl implements CommentBO {

    @Autowired
    private CommentDAO dao;
    @Autowired
    private LaunchEntityDTOMapper mapper;

    public CommentBOimpl() {
    }

    @Override
    public void saveComment(CommentDTO dto) throws Exception {
        dao.save(mapper.getComment(dto));
    }

    @Override
    public void updateComment(CommentDTO dto) throws Exception {
        dao.save(mapper.getComment(dto));
    }

    @Override
    public void deleteComment(int commentId) throws Exception {
        dao.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> getAllComments() throws Exception {
        return dao.findAll().stream().
                map(comment -> mapper.getCommentDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getComment(int commentId) throws Exception {
        return dao.findById(commentId).map(comment -> mapper.getCommentDTO(comment)).get();
    }

    @Override
    public List<CommentDTO> getLaunchComments(int launchId) {
        return dao.findLaunchComment(launchId).stream().map(
                comments -> mapper.getCommentDTO(comments)).collect(Collectors.toList()
        );
    }
}
