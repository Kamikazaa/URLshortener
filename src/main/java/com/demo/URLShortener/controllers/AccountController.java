package com.demo.URLShortener.controllers;


import com.demo.URLShortener.domain.Account;
import com.demo.URLShortener.exception.BadRequestException;
import com.demo.URLShortener.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(AccountController.BASE_URL)
public class AccountController {

    static final String BASE_URL = "/api/v1/URLshortener/";

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    private final AccountService accountService;

    @PostMapping(value = "/account", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerAccount(@RequestBody Account account) throws Exception {
        if (account.getAccountId() == null || account.getAccountId().equals("")) {
            throw new BadRequestException("Account ID invalid");
        } else {
            accountService.registerAccount(account);
            if (account.getSuccess())
                return account.toStringSuccessfulRegistration();
            else
                return account.toStringFailedRegistration();
        }
    }

}
