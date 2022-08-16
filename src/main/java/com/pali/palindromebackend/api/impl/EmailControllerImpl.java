package com.pali.palindromebackend.api.impl;

import com.pali.palindromebackend.api.EmailController;
import com.pali.palindromebackend.model.EmailDetails;
import com.pali.palindromebackend.service.EmailService.EmailService;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 8/16/2022
 **/
public class EmailControllerImpl extends EmailController {
    private final EmailService emailService;

    public EmailControllerImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public String sendMail(EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }
}
