package com.pali.palindromebackend.api;


import com.pali.palindromebackend.model.EmailDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/30/2022
 **/

@RestController
@RequestMapping("/api/v1/mails")
public abstract class EmailController {


    // Sending a simple Email
    @PostMapping("/sendMail")
    public abstract String sendMail(@RequestBody EmailDetails details);


    // Sending email with attachment
    @PostMapping(value = "/sendMailWithAttachment")
    public abstract String sendMailWithAttachment(@RequestBody EmailDetails details);

}
