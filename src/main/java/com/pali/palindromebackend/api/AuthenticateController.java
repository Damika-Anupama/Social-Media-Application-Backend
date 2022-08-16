package com.pali.palindromebackend.api;

import com.pali.palindromebackend.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/05/2021
 **/
@RestController
@RequestMapping(value = "/api/v1/authenticate")
public abstract class AuthenticateController {

    @PostMapping
    public abstract ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO loginDTO);
}

