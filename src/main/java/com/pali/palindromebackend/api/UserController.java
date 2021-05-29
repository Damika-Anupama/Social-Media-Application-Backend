package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.UserBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserBO bo;

    @Autowired
    private EntityDTOMapper mapper;

    public UserController() throws SQLException{

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getAllUsers() throws Exception {
        System.out.println("get");
        return new ResponseEntity<List<UserDTO>>(bo.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Integer userId) throws Exception {
        System.out.println("One");
        try {
            return new ResponseEntity<>(bo.getUser(userId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDTO dto) throws Exception {
        System.out.println(mapper.getUser(dto));
        try {
            bo.saveUser(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer userId) throws Exception {
        try {
            System.out.println(userId);
            bo.getUser(userId);
            bo.deleteUser(userId);
            return new ResponseEntity<>("Successfully deleted the user !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO dto, @PathVariable("userId") Integer userId)
            throws Exception {
        if (dto.getId() != userId) {
            return new ResponseEntity<>("Mismatch userId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.getUser(userId);
            bo.updateUser(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
