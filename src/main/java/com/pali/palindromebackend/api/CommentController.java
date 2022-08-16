package com.pali.palindromebackend.api;

import com.pali.palindromebackend.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 5/11/2022
 **/
@RestController
@RequestMapping("api/v1/comments")
public abstract class CommentController {



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getAllComments();

    @GetMapping(value = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getCommentById(@PathVariable("commentId") int comId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<?> saveComment(@Valid @RequestBody CommentDTO dto);

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public abstract ResponseEntity<?> deleteComment(@PathVariable("commentId") int comId);

    @PutMapping(
            value = "/{comId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> updateComment(@Valid @RequestBody CommentDTO dto, @PathVariable("comId") int comId);
}
