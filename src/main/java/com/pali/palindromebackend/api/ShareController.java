package com.pali.palindromebackend.api;

import com.pali.palindromebackend.dto.ShareDTO;
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
@RequestMapping("api/v1/shares")
public abstract class ShareController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getAllShares();

    @GetMapping(value = "/{shareId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getShareById(@PathVariable("shareId") int shareId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<?> saveShare(@Valid @RequestBody ShareDTO dto);

    @DeleteMapping("/{shareId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public abstract ResponseEntity<?> deleteShare(@PathVariable("shareId") int shareId);

    @PutMapping(
            value = "/{shareId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> updateShare(@Valid @RequestBody ShareDTO dto, @PathVariable("shareId") int shareId);

}
