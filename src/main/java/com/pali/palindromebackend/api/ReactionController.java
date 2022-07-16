package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.ReactionBO;
import com.pali.palindromebackend.dto.ReactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 10/05/2022
 **/
@RestController
@RequestMapping("/api/v1/reactions")
public class ReactionController {

    @Autowired
    private ReactionBO bo;

    public ReactionController() throws SQLException {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllReactions() throws Exception {
        try {
            return new ResponseEntity<List<ReactionDTO>>( bo.getAllReactions(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Status found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !! \n" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{ReactionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getReactionById(@PathVariable("ReactionId") int ReactionId) throws Exception {
        try {
            return new ResponseEntity<>(bo.getReaction(ReactionId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Status found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> saveReaction(ReactionDTO dto) throws Exception {
        System.out.println(dto);
        //reaction time and updated time should be filled from the frontend
        if(bo.checkUniqueness(dto.getUserId(),dto.getLaunchId())){
            return new ResponseEntity<>("Same user has already reacted to this launch." +
                    " So please update relevant data. You can't create another", HttpStatus.BAD_REQUEST);
        }
        try {
            Date date = new Date();
            dto.setReactionTime(date);
            dto.setUpdatedTime(date);
            bo.saveReaction(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{ReactionId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deleteReaction(@PathVariable("ReactionId") int ReactionId) throws Exception {
        try {
            bo.deleteReaction(ReactionId);
            return new ResponseEntity<>("Successfully deleted the Status !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Status is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{ReactionId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateReaction(@Valid @RequestBody ReactionDTO dto, @PathVariable("ReactionId") int ReactionId)
            throws Exception {
        if (dto.getId() != ReactionId) {
            return new ResponseEntity<>("Mismatch StatusId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.updateReaction(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Status is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
