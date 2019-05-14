package com.demo.URLShortener.services;

import com.demo.URLShortener.domain.RegisterUrl;

import java.util.Map;

public interface RegisterUrlService {
    RegisterUrl registerWebsite(RegisterUrl registerUrl, String user) throws Exception;
    Boolean checkShortUrl (String url);
    Map<String, Integer> getStatistics (String accountId);
}
