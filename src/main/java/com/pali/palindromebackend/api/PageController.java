package com.pali.palindromebackend.api;

import com.pali.palindromebackend.dto.PageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 19/06/2021
 **/
@RestController
@RequestMapping("/api/v1/page")
public abstract class PageController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> getAllPages();


    @GetMapping(value = "/{PageId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<Object> getPageById(@PathVariable("PageId") int PageId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public abstract ResponseEntity<?> savePage(@Valid @RequestBody PageDTO dto);

    @DeleteMapping("/{PageId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public abstract ResponseEntity<Object> deletePage(@PathVariable("PageId") int PageId);

    @PutMapping(
            value = "/{PageId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public abstract ResponseEntity<?> updatePage(@Valid @RequestBody PageDTO dto, @PathVariable("PageId") int PageId);
}
