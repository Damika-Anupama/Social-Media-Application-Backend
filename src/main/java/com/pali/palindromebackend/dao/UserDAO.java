package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.User;
import com.sun.el.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
public interface UserDAO extends JpaRepository<User,Integer> {

    Optional<User> findUserByUsername(String username);

}
