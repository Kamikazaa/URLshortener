package com.demo.URLShortener.controllers;

import com.demo.URLShortener.exception.UnauthorizedException;
import com.demo.URLShortener.services.RegisterUrlService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping(StatisticController.BASE_URL)
public class StatisticController {

    static final String BASE_URL = "/api/v1/URLshortener/";

    private RegisterUrlService registerUrlService;

    public StatisticController(RegisterUrlService registerUrlService) {
        this.registerUrlService = registerUrlService;
    }

    @GetMapping(value = "/statistic/{accountId}", produces = "application/json")
    public Map<String,Integer> fetchStatistic(@PathVariable String accountId) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        if (!currentUser.equals(accountId))
            throw new UnauthorizedException("User is not allowed to get statistics from other users");

        return registerUrlService.getStatistics(accountId);

    }
}
