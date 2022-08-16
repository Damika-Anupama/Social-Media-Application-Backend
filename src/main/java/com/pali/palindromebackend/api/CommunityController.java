package com.pali.palindromebackend.api;

import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.dto.CommunityLaunchDTO;
import com.pali.palindromebackend.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/
@RestController
@RequestMapping("api/v1/community")
public abstract class CommunityController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getAllCommunities();

    @GetMapping(value = "/{comId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getCommunityById(@PathVariable("comId") int comId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<Object> saveCommunity(@ModelAttribute CommunityUserBody body);

    @DeleteMapping("/{comId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public abstract ResponseEntity<?> deleteCommunity(@PathVariable("comId") int comId);

    @PutMapping(
            value = "/{comId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> updateCommunity(
            @Valid @RequestBody CommunityDTO dto, @PathVariable("comId") int comId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "/launch",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<Object> createCommunityLaunch(@ModelAttribute CommunityLaunchCreate body);


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<Object> shareCommunityLaunch(@ModelAttribute CommunityLaunchDTO dto);

}
