package com.pali.palindromebackend.dao;


import com.pali.palindromebackend.entity.Status;
import com.pali.palindromebackend.entity.Suggestion;
import com.pali.palindromebackend.entity.SuggestionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 18/06/2021
 **/
public interface StatusDAO extends JpaRepository<Status, Integer> {

}
