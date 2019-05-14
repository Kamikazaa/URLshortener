package com.demo.URLShortener.controllers;

import com.demo.URLShortener.exception.NotFoundException;
import com.demo.URLShortener.repositories.RegisterUrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;


@RestController
public class RedirectController {

    public RedirectController(RegisterUrlRepository registerUrlRepository) {
        this.registerUrlRepository = registerUrlRepository;
    }

    private final RegisterUrlRepository registerUrlRepository;

    @GetMapping("/{shortUrl}")
    public void redirect (HttpServletResponse httpServletResponse, @PathVariable String shortUrl) throws Exception {

        if (registerUrlRepository.checkShortUrl(shortUrl) == null) {
            throw new NotFoundException("Short URL not found");
        }
        else {
            String redirectUrl = registerUrlRepository.getLongUrl(shortUrl);
            int redirectType = Integer.valueOf(registerUrlRepository.getRedirectType(shortUrl));
            registerUrlRepository.updateCount(shortUrl);

            httpServletResponse.setStatus(redirectType);
            httpServletResponse.setHeader("Location", redirectUrl);
        }
    }

}
