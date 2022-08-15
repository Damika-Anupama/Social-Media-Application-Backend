package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.CommunityBO;
import com.pali.palindromebackend.business.custom.CommunityLaunchBO;
import com.pali.palindromebackend.business.custom.CommunityUserBO;
import com.pali.palindromebackend.dto.CommunityDTO;
import com.pali.palindromebackend.dto.CommunityLaunchDTO;
import com.pali.palindromebackend.dto.CommunityUserDTO;
import com.pali.palindromebackend.entity.Community;
import com.pali.palindromebackend.entity.ExistingStatus;
import com.pali.palindromebackend.entity.Launch;
import com.pali.palindromebackend.entity.Role;
import com.pali.palindromebackend.entity.custom.LaunchUserDetails;
import com.pali.palindromebackend.model.*;
import com.pali.palindromebackend.service.FileService;
import com.pali.palindromebackend.service.FullLaunchBodyPackager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @Autowired
    private CommunityUserBO bo1;
    @Autowired
    private CommunityLaunchBO bo2;
    @Autowired
    private FileService fileService;
    @Autowired
    private LaunchController launchController;
    @Autowired
    private FullLaunchBodyPackager packager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllComs(){
        try {
            List<CommunityDTO> allComs = bo.getAllComs();
            ArrayList<ResponseCommunityBody> dlds = new ArrayList<>();
            allComs.forEach(detail -> {
                byte[] groupIcon = fileService.getMedia(detail.getGroupIcon());
                byte[] wallPaper = fileService.getMedia(detail.getWallpaper());
                ResponseCommunityBody dld = new ResponseCommunityBody(
                        detail.getCommunityId(),
                        detail.getTitle(),
                        detail.getDescription(),
                        detail.getCreatedDate(),
                        groupIcon,
                        wallPaper
                );
                dlds.add(dld);
            });
            return new ResponseEntity<List<ResponseCommunityBody>>(dlds, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{comId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getComById(@PathVariable("comId") int comId){
        try {
            List<MiniUserCom> miniUserComs = new ArrayList<>();
            List<CommunityDashboardLaunchDetail> dashboardLaunchDetails = new ArrayList<>();
            CommunityDTO dto = bo.getCom(comId);
            List<MiniUserComDTO> communityUsers = bo1.getCommunityUsers(comId);
            // converting the whole miniUserComsDTO array
            // into miniUserComs array
            communityUsers.forEach(value -> {
                miniUserComs.add(new MiniUserCom(
                        value.getUserId(),
                        value.getUsername(),
                        value.getShortDescription(),
                        fileService.getMedia(value.getProfilePicture()),
                        value.getOnlineStatus(),
                        value.getJoinedDate(),
                        value.getUpdatedDate(),
                        value.getRole()
                ));
            });
            List<CommunityLaunchDetail> communityLaunches = bo2.getCommunityLaunches(comId);
            communityLaunches.forEach(value -> {
                DashboardLaunchDetail launch = packager.getLaunch(
                        new LaunchUserDetails(
                                value.getId(),
                                value.getMedia(),
                                value.getMediaType(),
                                value.getDescription(),
                                value.getFeeling(),
                                value.getUserId(),
                                value.getUserName(),
                                value.getShortDescription(),
                                value.getProfilePicture(),
                                value.getUserOnlineStatus(),
                                value.getUpdatedDate(),
                                value.getCreatedDate()
                        )
                );
                CommunityDashboardLaunchDetail communityDashboardLaunchDetail = new CommunityDashboardLaunchDetail(
                        launch.getLaunchId(),
                        launch.getFile(),
                        launch.getMediaType(),
                        launch.getDescription(),
                        launch.getFeeling(),
                        launch.getUserId(),
                        launch.getUserName(),
                        launch.getShortDescription(),
                        launch.getProfilePicture(),
                        launch.getUserOnlineStatus(),
                        launch.getUpdatedTime(),
                        launch.getCreatedDate(),
                        launch.getReactType(),
                        launch.getReactions(),
                        launch.getComments(),
                        value.getExistingStatus(),
                        value.getSharedPersonId(),
                        value.getSharedTime()
                );
                dashboardLaunchDetails.add(communityDashboardLaunchDetail);
            });

            CommunityFullDetails communityFullDetails = new CommunityFullDetails(
                    comId,
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getCreatedDate(),
                    this.fileService.getMedia(dto.getGroupIcon()),
                    this.fileService.getMedia(dto.getWallpaper()),
                    miniUserComs,
                    dashboardLaunchDetails
                    );
            return new ResponseEntity<>(communityFullDetails, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No friend found !!", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Object> saveCom(@ModelAttribute CommunityUserBody body) {
        try {
            CommunityDTO dto = new CommunityDTO();
            CommunityUserDTO dto1 = new CommunityUserDTO();
            FileService service = new FileService();

            //setting the community dto object
            dto.setTitle(body.getTitle());
            dto.setDescription(body.getDescription());
            dto.setCreatedDate(new Date());
            dto.setGroupIcon(service.saveFile(body.getGroupIcon(),"community/groupIcon/"));
            dto.setWallpaper(service.saveFile(body.getWallpaper(),"community/wallpaper/"));
            Community community = bo.saveCom(dto);
            //setting the userCommunity object
            dto1.setUserId(body.getUserId());
            dto1.setCommunityId(community.getCommunityId());
            dto1.setJoinedDate(new Date()); // User's joined date is equals to community created date.
            // TODO: 7/12/2022 updated date not saving in the database
            dto1.setUpdatedDate(new Date());
            dto1.setRole(Role.OWNER); // When a community creation,  the user should definitely be the owner.
            System.out.println(dto1);
            bo1.saveCommunityUser(dto1);
            return new ResponseEntity<>(dto1, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong !! " + e, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "/launch",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseBody
    public ResponseEntity<Object> createCommunityLaunch(@ModelAttribute CommunityLaunchCreate body) {
        try {
            ResponseEntity<Launch> launchResponseEntity = launchController.saveLaunch(body.getLaunchBody());
            CommunityLaunchDTO dto = new CommunityLaunchDTO(
                    launchResponseEntity.getBody().getId(),
                    body.getCommunityId(),
                    ExistingStatus.CREATED,
                    body.getSharedPersonId(),
                    new Date()
            );

            bo2.save(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong !! " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Object> shareCommunityLaunch(@ModelAttribute CommunityLaunchDTO dto) {
        try {
            bo2.save(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong !! " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
