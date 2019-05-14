package com.demo.URLShortener.services;

import com.demo.URLShortener.domain.RegisterUrl;
import com.demo.URLShortener.exception.BadRequestException;
import com.demo.URLShortener.repositories.RegisterUrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;
import com.demo.URLShortener.utilities.RandomStringGenerator;
import com.demo.URLShortener.utilities.RandomNumberGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterUrlServiceImpl implements RegisterUrlService {

    private final RegisterUrlRepository registerUrlRepository;

    public RegisterUrlServiceImpl(RegisterUrlRepository registerUrlRepository) {
        this.registerUrlRepository = registerUrlRepository;
    }

    @Override
    public RegisterUrl registerWebsite(RegisterUrl registerUrl, String user) throws Exception {

        if (registerUrl.getUrl() == null || registerUrl.getUrl().equals("") )
            throw new BadRequestException("Invalid URL in request, URL must be defined");
        else {
            //Check if URL is already registered with current user
            if (registerUrlRepository.checkLongUrl(registerUrl.getUrl(), user) == null ||
                    registerUrlRepository.checkLongUrl(registerUrl.getUrl(), user).equals("")) {

                String shortUrl = RandomStringGenerator.generateRandomString(RandomNumberGenerator.getRandomNumber(6,10));
                // Check if defined short url exists, if yes generate new one
                if (!checkShortUrl(shortUrl)) {
                    registerUrl.setAccount(user);
                    registerUrl.setShortUrl(shortUrl);
                    registerUrl.setCount(0);

                    // Check if redirect type exists
                    if (registerUrl.getRedirectType() == null)
                        registerUrl.setRedirectType("302");
                    else if (!(registerUrl.getRedirectType().equals("301") || registerUrl.getRedirectType().equals("302")))
                        throw new BadRequestException("Invalid Redirect type, must be 301, 302 or undefined");

                    registerUrlRepository.save(registerUrl);
                }
                else
                    registerWebsite(registerUrl, user);
            }
            else
                throw new BadRequestException("URL already defined with this user");
        }
        return registerUrl;
    }

    @Override
    public Boolean checkShortUrl(String url) {
        return registerUrlRepository.existsById(url);
    }

    @Override
    public Map<String, Integer> getStatistics(String accountId) {
        List<RegisterUrl> registerUrls = registerUrlRepository.findByAccount(accountId);

        Map<String, Integer> map = new HashMap<>();
        for (RegisterUrl x:
                registerUrls) {
            map.put(x.getUrl(), x.getCount());
        }
        return map;
    }
}
