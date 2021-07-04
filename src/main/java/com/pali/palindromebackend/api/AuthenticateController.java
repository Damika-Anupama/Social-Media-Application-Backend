package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.util.MyUserDetailsService;
import com.pali.palindromebackend.dao.UserDAO;
import com.pali.palindromebackend.dto.LoginDTO;
import com.pali.palindromebackend.entity.User;
import com.pali.palindromebackend.model.AuthenticateBody;
import com.pali.palindromebackend.service.FileService;
import com.pali.palindromebackend.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/05/2021
 **/
@RestController
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDAO userDAO;


    @Autowired
    private FileService fileService;

    @PostMapping(value = "/api/v1/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO loginDTO) throws Exception {
        System.out.println(loginDTO);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

        }catch (DisabledException e){
            return new ResponseEntity<>("You are deactivated! please contact the admin", HttpStatus.FORBIDDEN);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }catch (Throwable e){
            throw  new Error(e);
        }
        Optional<User> user = userDAO.findUserByUsername(loginDTO.getUsername());
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        byte[] media = fileService.getMedia(user.get().getProfilePicture());
        AuthenticateBody body = new AuthenticateBody(jwt, user.get().getId(), media, user.get().getUsername());
        return ResponseEntity.ok(body);
    }
}

