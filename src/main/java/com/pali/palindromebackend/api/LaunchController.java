package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.LaunchBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.model.LaunchBody;
import com.pali.palindromebackend.model.ResponseLaunchBody;
import com.pali.palindromebackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
@RestController
@RequestMapping("/api/v1/launches")
public class LaunchController {
    @Autowired
    private LaunchBO bo;

    @Autowired
    private FileService fileService;

    private EntityDTOMapper mapper;

    public LaunchController() throws SQLException {
    }


    @PreAuthorize("permitAll()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllLaunches() throws Exception {
        try {
            List<LaunchDTO> launches = bo.getAllLaunches();
            List<ResponseLaunchBody> launches1 = new ArrayList<ResponseLaunchBody>();
            launches.forEach(dto -> {
                ResponseLaunchBody launchBody = new ResponseLaunchBody();
                launchBody.setFile(fileService.getMedia(dto.getMedia()));
                launchBody.setDescription(dto.getDescription());
                launchBody.setFeeling(dto.getFeeling());
                launches1.add(launchBody);
            });
            return new ResponseEntity<>(launches1, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getLaunchesByUserId(@PathVariable("userId") int userId)throws Exception{
        try{
            List<LaunchDTO> launches = bo.getLaunchesByUserId(userId);
            List<ResponseLaunchBody> launches1 = new ArrayList<ResponseLaunchBody>();
            launches.forEach(dto -> {
                ResponseLaunchBody launchBody = new ResponseLaunchBody();
                launchBody.setFile(fileService.getMedia(dto.getMedia()));
                launchBody.setDescription(dto.getDescription());
                launchBody.setFeeling(dto.getFeeling());
                launches1.add(launchBody);
            });
            return new ResponseEntity<>(launches1,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("No Launch Found !!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong !!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{launchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getLaunchById(@PathVariable("launchId") int launchId) throws Exception {
        try {
            return new ResponseEntity<>(bo.getLaunch(launchId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No Launch Found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseBody
    public ResponseEntity<Object> saveLaunch(@ModelAttribute LaunchBody body) throws Exception {
        try {
            final String mediaPath = fileService.saveMediaFile(body.getFile(), body.getFile().getContentType());
            LaunchDTO dto = new LaunchDTO();
            dto.setDescription(body.getDescription());
            dto.setFeeling(body.getFeeling());
            dto.setMedia(mediaPath);
            dto.setUser(body.getUser());
            bo.saveLaunch(dto);
            return new ResponseEntity<>(dto.getUser(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{launchId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deleteLaunch(@PathVariable("launchId") int launchId) throws Exception {
        try {
            System.out.println(launchId);
            bo.getLaunch(launchId);
            bo.deleteLaunch(launchId);
            return new ResponseEntity<>("Successfully deleted the relevant launch :)", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No launch is found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{launchId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> updateLaunch(@Valid @RequestBody LaunchDTO dto,
                                               @PathVariable("launchId") int launchId) throws Exception {
        if (!(dto.getId() == launchId)) {
            return new ResponseEntity<>("Mismatch launchId :(", HttpStatus.BAD_REQUEST);
        }
        try {
            bo.getLaunch(launchId);
            bo.updateLaunch(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No such launch is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
