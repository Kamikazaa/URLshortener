package com.demo.URLShortener.controllers;

import com.demo.URLShortener.domain.RegisterUrl;
import com.demo.URLShortener.services.RegisterUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RegisterUrlController.BASE_URL)
public class RegisterUrlController {

    static final String BASE_URL = "/api/v1/URLshortener/";
    private final RegisterUrlService registerUrlService;

    public RegisterUrlController(RegisterUrlService registerUrlService) {
        this.registerUrlService = registerUrlService;
    }

    @PostMapping(value = "/register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUrl(@RequestBody RegisterUrl registerUrl) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        registerUrlService.registerWebsite(registerUrl, currentUser);

        return registerUrl.shortUrlJson();
    }

}
