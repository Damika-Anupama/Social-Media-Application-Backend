package com.pali.palindromebackend;

import com.pali.palindromebackend.util.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class PalindromeBackendApplication {

    public static void main(String[] args) {
        LogConfig.initLogging();
        SpringApplication.run(PalindromeBackendApplication.class, args);
    }
}
