package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.SuggestionBO;
import com.pali.palindromebackend.business.util.SuggestionEntityDTOMapper;
import com.pali.palindromebackend.dto.SuggestionDTO;
import com.pali.palindromebackend.dto.SuggestionUserDTO;
import com.pali.palindromebackend.model.ResponseSuggestionUser;
import com.pali.palindromebackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 19/06/2021
 **/
@RestController
@RequestMapping("/api/v1/suggestion")
public class SuggestionController {
    @Autowired
    private SuggestionBO bo;

    @Autowired
    private SuggestionEntityDTOMapper mapper;

    @Autowired
    private FileService fileService;

    public SuggestionController() throws SQLException {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllSuggestions() throws Exception {
        try {
            return new ResponseEntity<List<SuggestionDTO>>( bo.getAllSuggestions(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Suggestion found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllSuggestionsWithUsers() throws Exception {
        try {
            List<SuggestionUserDTO> dtos = bo.getAllSuggestionsWithUsers();
            ArrayList<ResponseSuggestionUser> list = new ArrayList<>();
            dtos.forEach(dto ->{
                ResponseSuggestionUser rsu = new ResponseSuggestionUser();
                rsu.setProfilePicture(fileService.getMedia(dto.getProfilePicture()));
                rsu.setId(dto.getId());
                rsu.setMessage(dto.getMessage());
                rsu.setPriority(dto.getPriority());
                rsu.setFeeling(dto.getFeeling());
                rsu.setUsername(dto.getUsername());
                rsu.setUserId(dto.getUserId());
                list.add(rsu);
            });
            return new ResponseEntity<List<ResponseSuggestionUser>>(list, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Suggestion found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{suggestionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getSuggestionById(@PathVariable("suggestionId") int suggestionId){
        try {
            return new ResponseEntity<>(bo.getSuggestion(suggestionId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Suggestion found !!", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> saveSuggestion(@Valid @RequestBody SuggestionDTO dto){
        try {
            bo.saveSuggestion(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Something went wrong when saving the suggestion",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{suggestionId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deleteSuggestion(@PathVariable("suggestionId") int suggestionId){
        try {
            bo.deleteSuggestion(suggestionId);
            return new ResponseEntity<>("Successfully deleted the Suggestion !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Suggestion is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{suggestionId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateSuggestion(
            @Valid @RequestBody SuggestionDTO dto,
            @PathVariable("suggestionId") int suggestionId){
        if (dto.getId() != suggestionId) {
            return new ResponseEntity<>("Mismatch suggestionId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.updateSuggestion(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Suggestion is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
