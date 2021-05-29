package com.pali.palindromebackend;

import com.pali.palindromebackend.util.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PalindromeBackendApplication {

    public static void main(String[] args) {
        LogConfig.initLogging();
        SpringApplication.run(PalindromeBackendApplication.class, args);
    }

}
