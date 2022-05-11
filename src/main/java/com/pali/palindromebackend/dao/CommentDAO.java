package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 5/11/2022
 **/
public interface CommentDAO extends JpaRepository<Comment,Integer> {
}
