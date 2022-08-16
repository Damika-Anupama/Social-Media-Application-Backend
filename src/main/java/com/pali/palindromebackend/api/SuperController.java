package com.pali.palindromebackend.api;

import com.pali.palindromebackend.dto.SuperDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 8/16/2022
 **/
public interface SuperController {
    ResponseEntity<?> save(SuperDTO superDTO);

    ResponseEntity<?> findById(int id);

    ResponseEntity<?> findAll();

    ResponseEntity<?> update(SuperDTO superDTO);

    ResponseEntity<?> deleteById(int id);
}
