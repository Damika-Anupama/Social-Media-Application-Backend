package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.FriendBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.business.util.FriendEntityDTOMapper;
import com.pali.palindromebackend.dto.FriendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
@RestController
@RequestMapping("api/v1/friends")
public class FriendController {

    @Autowired
    private FriendBO bo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllFriends() throws Exception {
        try {
            return new ResponseEntity<List<FriendDTO>>(bo.getAllFriends(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{friendshipId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getFriendById(@PathVariable("friendshipId") int friendshipId) throws Exception {
        try {
            return new ResponseEntity<>(bo.getFriend(friendshipId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> saveFriend(@Valid @RequestBody FriendDTO dto) throws IOException {
        try {
            bo.saveFriend(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{friendshipId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<?> deleteFriend(@PathVariable("friendshipId") int friendshipId) throws Exception {
        try {
            System.out.println(friendshipId);
            bo.getFriend(friendshipId);
            bo.deleteFriend(friendshipId);
            return new ResponseEntity<>("Successfully deleted the friend !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{friendshipId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateFriend(@Valid @RequestBody FriendDTO dto, @PathVariable("friendshipId") int friendshipId)
            throws Exception {
        if (dto.getFriendId() != friendshipId) {
            return new ResponseEntity<>("Mismatch friendshipId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.getFriend(friendshipId);
            bo.updateFriend(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
