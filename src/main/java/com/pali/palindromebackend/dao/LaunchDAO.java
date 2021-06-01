package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Launch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
public interface LaunchDAO extends JpaRepository<Launch,String> {
}
