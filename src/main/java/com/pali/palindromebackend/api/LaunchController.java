package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.CommentBO;
import com.pali.palindromebackend.business.custom.LaunchBO;
import com.pali.palindromebackend.business.custom.ReactionBO;
import com.pali.palindromebackend.business.custom.UserBO;
import com.pali.palindromebackend.dto.CommentDTO;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.dto.ReactionDTO;
import com.pali.palindromebackend.dto.UserDTO;
import com.pali.palindromebackend.entity.custom.LaunchUserDetails;
import com.pali.palindromebackend.model.*;
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
import java.util.Date;
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
    private ReactionBO bo1;
    @Autowired
    private CommentBO bo2;
    @Autowired
    private UserBO bo3;

    @Autowired
    private FileService fileService;

    public LaunchController() throws SQLException {
    }

    @PreAuthorize("permitAll()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllLaunchesWithUserDetails() throws Exception {
        try {
            List<LaunchUserDetails> luds = bo.getAllLaunchesWithUserDetails();
            ArrayList<DashboardLaunchDetail> dlds = new ArrayList<>();
            luds.forEach(detail -> {
                ArrayList<LaunchReactionBody> lr = new ArrayList<>();
                ArrayList<LaunchCommentBody> lc = new ArrayList<>();
                byte[] launchMedia = fileService.getMedia(detail.getMedia());
                byte[] userMedia = fileService.getMedia(detail.getProfilePicture());

                List<ReactionDTO> reactions = bo1.getLaunchReactions(detail.getId());
                List<CommentDTO> comments = bo2.getLaunchComments(detail.getId());

                reactions.forEach(reaction -> {
                    UserDTO user = null;
                    LaunchReactionBody body;
                    byte[] picture = new byte[0];

                    try {
                        user = bo3.getUser(reaction.getUserId());
                        picture = fileService.getMedia(user.getProfilePicture());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    assert user != null;
                    body = new LaunchReactionBody(
                            reaction.getId(),
                            reaction.getType(),
                            reaction.getReactionTime(),
                            reaction.getUpdatedTime(),
                            user.getId(),
                            user.getUsername(),
                            picture,
                            user.getIsActive()
                    );
                    lr.add(body);
                });

                comments.forEach(comment -> {
                    UserDTO user = null;
                    LaunchCommentBody body;
                    byte[] picture = new byte[0];

                    try {
                        user = bo3.getUser(comment.getUser());
                        picture = fileService.getMedia(user.getProfilePicture());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    assert user != null;
                    body = new LaunchCommentBody(
                            comment.getId(),
                            comment.getComment(),
                            comment.getCommentedDate(),
                            comment.getLastUpdatedDate(),
                            user.getId(),
                            user.getUsername(),
                            picture,
                            user.getIsActive()
                    );
                    lc.add(body);
                });

                DashboardLaunchDetail dld = new DashboardLaunchDetail(
                        detail.getId(),
                        launchMedia,
                        detail.getMediaType(),
                        detail.getDescription(),
                        detail.getFeeling(),
                        detail.getUserId(),
                        detail.getUserName(),
                        detail.getShortDescription(),
                        userMedia,
                        detail.getUpdatedDate(),
                        lr,
                        lc
                );
                dlds.add(dld);
            });
            return new ResponseEntity<>(dlds, HttpStatus.OK);
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
            Date date = new Date();

            dto.setDescription(body.getDescription());
            dto.setFeeling(body.getFeeling());
            dto.setMedia(mediaPath);
            dto.setMediaType(body.getFile().getContentType());
            dto.setUser(body.getUser());
            dto.setCreatedDate(date);
            dto.setUpdatedDate(date);
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
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateLaunch(@Valid @ModelAttribute LaunchBody body,
                                               @PathVariable("launchId") int launchId) throws Exception {
        System.out.println("Hello");
        try {
            String filePath = null;
            LaunchDTO dto = bo.getLaunch(launchId);
            Date date = new Date();

            if (!(dto.getUser() == body.getUser())) {
                return new ResponseEntity<>("Mismatched User :(", HttpStatus.BAD_REQUEST);
            }

            if (body.getFile() != null) {
                // TODO: 5/26/2022  this must contain transaction method
                filePath = fileService.saveMediaFile(body.getFile(),body.getFile().getContentType());
                fileService.deleteFile(dto.getMedia());
            }
            dto.setMedia(filePath);
            dto.setDescription(body.getDescription());
            dto.setUpdatedDate(date);
            dto.setFeeling(body.getFeeling()); // user of the launch doesn't change
            bo.updateLaunch(dto);
            return new ResponseEntity<>("Successfully the launch was updated!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No such launch is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
