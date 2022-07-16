package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/
public interface CommunityDAO extends JpaRepository<Community,Integer> {

}
