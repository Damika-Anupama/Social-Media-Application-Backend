package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.*;
import com.pali.palindromebackend.dto.*;
import com.pali.palindromebackend.entity.custom.LaunchUserDetails;
import com.pali.palindromebackend.model.*;
import com.pali.palindromebackend.service.DateDescendentObjectCreator;
import com.pali.palindromebackend.service.FileService;
import com.pali.palindromebackend.service.FullLaunchBodyPackager;
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
 * @since : 28/04/2021
 **/

// TODO: 6/3/2022 Lock the user object while it's on use
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserBO userBO;

    @Autowired
    private CommunityUserBO communityUserBO;

    @Autowired
    private CommunityBO communityBO;

    @Autowired
    private FriendBO friendBO;

    @Autowired
    private LaunchBO launchBO;

    @Autowired
    private FileService fileService;

    @Autowired
    private FullLaunchBodyPackager packager;

    @Autowired
    private DateDescendentObjectCreator creator;

    public UserController() throws SQLException {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllUsers() throws Exception {
        try {
            return new ResponseEntity<List<UserDTO>>(userBO.getAllUsers(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // send data to the profile page
    @GetMapping(value = "/info/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getUserInfoForProfilePage(@PathVariable("userId") int userId) {
        try {
            UserDTO user = userBO.getUser(userId);

            List<LaunchDTO> launchesByUserId = launchBO.getLaunchesByUserId(userId);
            ArrayList<DashboardLaunchDetail> launches = new ArrayList<>();
            launchesByUserId.forEach(dto -> {
                UserDTO user1 = null;
                try {
                    user1 = userBO.getUser(dto.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DashboardLaunchDetail launch = packager.getLaunch(
                        new LaunchUserDetails(
                                dto.getId(),
                                dto.getMedia(),
                                dto.getMediaType(),
                                dto.getDescription(),
                                dto.getFeeling(),
                                user1.getId(),
                                user1.getUsername(),
                                user1.getShortDescription(),
                                user1.getProfilePicture(),
                                user1.getUpdatedAt(),
                                user1.getCreatedAt()
                        )
                );
                launches.add(launch);
            });

            List<CommunityUserDTO> communitiesUsers = communityUserBO.getAllCommunitiesByUserId(userId);
            ArrayList<ResponseCommunityBody> communities = new ArrayList<>();
            communitiesUsers.forEach(c ->{
                CommunityDTO com = null;
                try {
                    com = communityBO.getCom(c.getCommunityId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ResponseCommunityBody body = new ResponseCommunityBody(
                        com.getCommunityId(),
                        com.getTitle(),
                        com.getDescription(),
                        com.getCreatedDate(),
                        fileService.getMedia(com.getGroupIcon()),
                        fileService.getMedia(com.getWallpaper())
                );
                communities.add(body);
            });

            List<FriendDTO> friendsByUserId = friendBO.getAllFriendsByUserId(userId);
            ArrayList<RequiredFriendDetailObject> friends = new ArrayList<>();
            friendsByUserId.forEach(friend->{
                UserDTO user1 = null;
                RequiredFriendDetailObject object = new RequiredFriendDetailObject();
                int friendId = 0;
                if(userId == friend.getFriend1()){
                    friendId = friend.getFriend2();
                    object.setAsked(false);
                }else {
                    friendId = friend.getFriend1();
                    object.setAsked(true);
                }
                object.setFriendId(friendId);
                object.setCreatedDate(friend.getFriendshipDate());
                try {
                    user1 = userBO.getUser(friendId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                object.setUsername(user1.getUsername());
                object.setProfilePicture(fileService.getMedia(user1.getProfilePicture()));
                friends.add(object);
            });

            List<DateDescendObject> dateDescendObjects = creator.giveDateDescendentObjet(launches, communities, friends);

            UserProfileBody userProfileBody = new UserProfileBody(
                    userId,
                    user.getCreatedAt(),
                    user.getIsActive(),
                    user.getRole(),
                    user.getUpdatedAt(),
                    user.getLastLogin(),
                    user.getOnlineStatus(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getGender(),
                    user.getEmail(),
                    user.getShortDescription(),
                    fileService.getMedia(user.getProfilePicture()),
                    user.getContactNum(),
                    user.getLocation(),
                    user.getEducation(),
                    user.getSkills(),
                    user.getDob(),
                    user.getRelationship(),
                    communitiesUsers.size(),
                    friendsByUserId.size(),
                    launchesByUserId.size(),
                    dateDescendObjects
                    );


            return new ResponseEntity<UserProfileBody>(userProfileBody, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong !!\n"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //

    @GetMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("userId") int userId){
        try {
            UserDTO user = userBO.getUser(userId);
            SendingUserBody body = new SendingUserBody(
                    fileService.getMedia(user.getProfilePicture()),
                    user.getShortDescription(), user.getUsername(),
                    user.getEmail(),
                    user.getContactNum(),
                    user.getPassword()
            );
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/picture/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getUserProfilePicture(@PathVariable("id") int id) throws Exception {
        try {
            HomePageLoading hpl = new HomePageLoading(fileService.getMedia(userBO.getUserProfilePicture(id)));
            return new ResponseEntity<>(hpl, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/name/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getUserByName(@PathVariable("userName") String userName) throws Exception {
        System.out.println("getUserByName");
        try {
            return new ResponseEntity<>(userBO.getUserByName(userName), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO dto) throws Exception {
        try {
            System.out.println(userBO.saveUser(dto));
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") int userId){
        try {
            System.out.println(userId);
            userBO.getUser(userId);
            userBO.deleteUser(userId);
            return new ResponseEntity<>("Successfully deleted the user !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping(
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ResponseEntity<?> updateUser( @ModelAttribute UserBody body){
//                try {
//            String filePath = null;
//            if (body.getProfilePic() != null) {
//                filePath = fileService.saveUserProfilePicture(body.getProfilePic());
//                UserDTO user = bo.getUser(body.getId());
//                fileService.deleteFile(user.getProfilePicture());
//            }
//            UserDTO dto = new UserDTO();
//            dto.setId(body.getId());
//            dto.setUsername(body.getUsername());
//            dto.setEmail(body.getEmail());
//            dto.setShortDescription(body.getShortDes());
//            dto.setProfilePicture(filePath);
//            dto.setContactNum(body.getPhoneNum());
////            dto.setOnlineStatus(true);
//            bo.updateUserNormalDetails(dto);
//            return new ResponseEntity<>(dto, HttpStatus.CREATED);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @PutMapping(
            value = "/{userid}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateUser( @ModelAttribute UserBody body, @PathVariable("userid") int userid){
        if (body.getId() != userid) {
            return new ResponseEntity<>("Mismatch userId !!", HttpStatus.BAD_REQUEST);
        } else if (true) {
            // TODO: 5/26/2022   handle the multi part file size from this stage before save it
        }
        try {
            String filePath = null;
            if (body.getProfilePic() != null) {
                filePath = fileService.saveUserProfilePicture(body.getProfilePic());
                UserDTO user = userBO.getUser(userid);
                fileService.deleteFile(user.getProfilePicture());
            }
            UserDTO dto = new UserDTO();
            dto.setId(body.getId());
            dto.setUsername(body.getUsername());
            dto.setEmail(body.getEmail());
            dto.setShortDescription(body.getShortDes());
            dto.setProfilePicture(filePath);
            dto.setContactNum(body.getPhoneNum());
//            dto.setOnlineStatus(true);
            userBO.updateUserNormalDetails(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Only use this method when the authentication method runs in AuthenticateController.java
    // TODO: 7/17/2022 Settle the error, when a user is logging to the application 
    public void updateUserLastLogin(UserBody body, int id){
        try {
            UserDTO dto = new UserDTO();
            dto.setLastLogin(body.getLastLogin());
            userBO.updateUserNormalDetails(dto);
            System.out.println("Successfully changed " + id + "'s last login.");
        } catch (NoSuchElementException e) {
            System.out.println("No user is found !!");
        } catch (Exception e) {
            System.out.println("Something went wrong !! " + e);
        }
    }
}
