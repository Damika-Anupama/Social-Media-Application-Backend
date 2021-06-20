package com.pali.palindromebackend.api;

import com.pali.palindromebackend.business.custom.UserBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dto.UserDTO;
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

    @Autowired
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

    @GetMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("userId") int userId) throws Exception {
        System.out.println("getUserById");
        try {
            return new ResponseEntity<>(bo.getUser(userId), HttpStatus.OK);
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
    @PostMapping()
    @ResponseBody
    public ResponseEntity<Object> saveUser(@ModelAttribute UserBody body) throws Exception {
        System.out.println(body);
        try {
            String filePath = null;
            if(body.getProfilePic()!=null) {
                filePath = fileService.saveUserProfilePicture(body.getProfilePic());
            }
            UserDTO dto = new UserDTO();
            dto.setUsername(body.getUsername());
            dto.setEmail(body.getEmail());
            dto.setPassword(body.getPassword());
            dto.setGender(body.getGender());
            dto.setCreatedAt(body.getCreatedAt());
            dto.setRole(body.getRole());
            dto.setPhoneNum(body.getPhoneNum());
            dto.setIsActive(body.isActive());
            dto.setShortDescription(body.getShortDes());
            dto.setProfilePicture(filePath);
            bo.saveUser(dto);
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
            value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> updateUser(@ModelAttribute UserBody body,@PathVariable("userId") int userId)
            throws Exception {
        if (body.getUserId() != userId) {
            return new ResponseEntity<>("Mismatch userId !!", HttpStatus.BAD_REQUEST);
        }
        try {
            String filePath = null;
            if(body.getProfilePic()!=null) {
                filePath = fileService.saveUserProfilePicture(body.getProfilePic());
                UserDTO user = bo.getUser(userId);
                fileService.deleteFile(user.getProfilePicture());
            }
            UserDTO dto = new UserDTO();
            dto.setUsername(body.getUsername());
            dto.setEmail(body.getEmail());
            dto.setPassword(body.getPassword());
            dto.setGender(body.getGender());
            dto.setCreatedAt(body.getCreatedAt());
            dto.setRole(body.getRole());
            dto.setPhoneNum(body.getPhoneNum());
            dto.setIsActive(body.isActive());
            dto.setShortDescription(body.getShortDes());
            dto.setProfilePicture(filePath);
            bo.updateUser(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>("No user is found !!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
