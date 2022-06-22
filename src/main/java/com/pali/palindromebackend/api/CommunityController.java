package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.CommunityBO;
import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.dto.CommunityUserDTO;
import com.pali.palindromebackend.entity.Role;
import com.pali.palindromebackend.model.CommunityUserBody;
import com.pali.palindromebackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 17/06/2021
 **/
@RestController
@RequestMapping("api/v1/community")
public class CommunityController {

    @Autowired
    private CommunityBO bo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllComs() throws Exception {
        try {
            return new ResponseEntity<List<CommunityDTO>>(bo.getAllComs(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{comId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getComById(@PathVariable("comId") int comId) throws Exception {
        try {
            return new ResponseEntity<>(bo.getCom(comId), HttpStatus.OK);
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
    public ResponseEntity<?> saveCom(@Valid @RequestBody CommunityUserBody body) throws IOException {
        try {
            // TODO: 6/21/2022  update the user community table
            CommunityDTO dto = new CommunityDTO();
            CommunityUserDTO dto1 = new CommunityUserDTO();
            FileService service = new FileService();

            //setting the community dto object
            dto.setTitle(body.getTitle());
            dto.setDescription(body.getDescription());
            dto.setCreatedDate(new Date());
            dto.setGroupIcon(service.saveCommunityGroupIcon(body.getGroupIcon()));
            dto.setWallpaper(service.saveCommunityWallpaper(body.getWallpaper()));
            //setting the userCommunity object
            dto1.setUserId(body.getUserId());
            dto1.setCommunityId(body.getCommunityId());
            dto1.setJoinedDate(new Date()); // User's joined date is equals to community created date.
            dto1.setRole(Role.OWNER); // When a community creation,  the user should definitely be the owner.
            // TODO: 6/21/2022 settle the saving user community in the backend 
            bo.saveCom(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{comId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<?> deleteCom(@PathVariable("comId") int comId) throws Exception {
        try {
            bo.getCom(comId);
            bo.deleteCom(comId);
            return new ResponseEntity<>("Successfully deleted the com !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{comId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateCom(@Valid @RequestBody CommunityDTO dto, @PathVariable("comId") int comId)
            throws Exception {
        if (dto.getCommunityId() != comId) {
            return new ResponseEntity<>("Mismatch comId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.getCom(comId);
            bo.updateCom(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No com is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
