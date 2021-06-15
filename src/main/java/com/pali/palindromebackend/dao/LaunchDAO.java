package com.pali.palindromebackend.dao;

import com.pali.palindromebackend.entity.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
public interface LaunchDAO extends JpaRepository<Launch,Integer> {

    @Query("select new com.pali.palindromebackend.entity.Launch (l.id,l.media,l.description,l.feeling,l.user) from Launch l where l.user.id like ?1")
    List<Launch> findLaunchesByUserId(int userId);
}
