package com.demo.URLShortener.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HelpController.BASE_URL)
public class HelpController {
    static final String BASE_URL = "/api/v1/URLshortener";

    @GetMapping("/help")
    public String help(){
        String message = "To learn how to use the application go to the following link:\n";
        String link = "https://documenter.getpostman.com/view/7402150/S1LyVTKx?version=latest";
        message = message + link;

        return message;
    }

}
