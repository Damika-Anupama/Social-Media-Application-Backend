package com.pali.palindromebackend.api;

import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
@RestController
@RequestMapping("/api/v1/launches")
abstract public class LaunchController {

    @PreAuthorize("permitAll()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getAllLaunchesWithUserDetails();

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getLaunchesByUserId(@PathVariable("userId") int userId) ;


    @GetMapping(value = "/{launchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<Object> getLaunchById(@PathVariable("launchId") int launchId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<Launch> saveLaunch(@ModelAttribute LaunchBody body) ;

    @DeleteMapping("/{launchId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public abstract ResponseEntity<Object> deleteLaunch(@PathVariable("launchId") int launchId);

    @PutMapping(
            value = "/{launchId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> updateLaunch(@Valid @ModelAttribute LaunchBody body,
                                          @PathVariable("launchId") int launchId) ;
}
