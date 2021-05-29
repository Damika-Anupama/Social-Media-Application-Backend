package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface UserDAO extends JpaRepository<User,Integer> {
}
