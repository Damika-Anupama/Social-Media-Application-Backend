package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.UserBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dto.UserDTO;
import com.pali.palindromebackend.model.HomePageLoading;
import com.pali.palindromebackend.model.SendingUserBody;
import com.pali.palindromebackend.model.UserBody;
import com.pali.palindromebackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since : 28/04/2021
 **/
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserBO bo;

    private EntityDTOMapper mapper;

    @Autowired
    private FileService fileService;

    public UserController() throws SQLException {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAllUsers() throws Exception {
        try {
            return new ResponseEntity<List<UserDTO>>(bo.getAllUsers(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // send data to load the profile page
    @GetMapping(value = "/info/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getUserInfoForProfilePage(@PathVariable("userId") int userId) throws Exception {
        try {
            return new ResponseEntity<List<UserDTO>>(bo.getAllUsers(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //

    @GetMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("userId") int userId) throws Exception {
        try {
            UserDTO user = bo.getUser(userId);
            SendingUserBody body = new SendingUserBody(fileService.getMedia(user.getProfilePicture()), user.getShortDescription(), user.getUsername(), user.getEmail(), user.getContactNum(), user.getPassword());
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
            HomePageLoading hpl = new HomePageLoading(fileService.getMedia(bo.getUserProfilePicture(id)));
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
            return new ResponseEntity<>(bo.getUserByName(userName), HttpStatus.OK);
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
            System.out.println(bo.saveUser(dto));
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") int userId) throws Exception {
        try {
            System.out.println(userId);
            bo.getUser(userId);
            bo.deleteUser(userId);
            return new ResponseEntity<>("Successfully deleted the user !!", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateUser(@Valid @ModelAttribute UserBody body, @PathVariable("id") int id)
            throws Exception {
        System.out.println("OK");
        if (body.getId() != id) {
            return new ResponseEntity<>("Mismatch userId !!", HttpStatus.BAD_REQUEST);
        } else if (true) {
            // TODO: 5/26/2022   handle the multi part file size from this stage before save it
        }
        try {
            String filePath = null;
            if (body.getProfilePic() != null) {
                filePath = fileService.saveUserProfilePicture(body.getProfilePic());
                UserDTO user = bo.getUser(id);
                fileService.deleteFile(user.getProfilePicture());
            }
            UserDTO dto = new UserDTO();
            dto.setId(body.getId());
            dto.setUsername(body.getUsername());
            dto.setEmail(body.getEmail());
            dto.setShortDescription(body.getShortDes());
            dto.setProfilePicture(filePath);
            dto.setContactNum(body.getPhoneNum());
            bo.updateUserNormalDetails(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Only use this method when the authentication method runs in AuthenticateController.java
    public void updateUserLastLogin(UserBody body, int id)
            throws Exception {
        try {
            UserDTO dto = new UserDTO();
            dto.setLastLogin(body.getLastLogin());
            bo.updateUserNormalDetails(dto);
            System.out.println("Successfully changed " + id + "'s last login.");
        } catch (NoSuchElementException e) {
            System.out.println("No user is found !!");
        } catch (Exception e) {
            System.out.println("Something went wrong !! " + e);
        }
    }
}
