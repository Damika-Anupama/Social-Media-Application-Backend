package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.PageBO;
import com.pali.palindromebackend.business.util.PageEntityDTOMapper;
import com.pali.palindromebackend.dto.PageDTO;
import com.pali.palindromebackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * @since : 19/06/2021
 **/
@RestController
@RequestMapping("/api/v1/page")
public class PageController {
    @Autowired
    private PageBO bo;

    @Autowired
    private PageEntityDTOMapper mapper;

    @Autowired
    private FileService fileService;

    public PageController() throws SQLException {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllPagees() throws Exception {
        try {
            return new ResponseEntity<List<PageDTO>>( bo.getAllPages(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Page found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{PageId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getPageById(@PathVariable("PageId") int PageId) throws Exception {
        try {
            return new ResponseEntity<>(bo.getPage(PageId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Page found !!", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> savePage(@Valid @RequestBody PageDTO dto) throws Exception {
        try {
            bo.savePage(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{PageId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deletePage(@PathVariable("PageId") int PageId) throws Exception {
        try {
            bo.deletePage(PageId);
            return new ResponseEntity<>("Successfully deleted the Page !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Page is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{PageId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updatePage(@Valid @RequestBody PageDTO dto, @PathVariable("PageId") int PageId)
            throws Exception {
        if (dto.getPageId() != PageId) {
            return new ResponseEntity<>("Mismatch PageId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.updatePage(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Page is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
