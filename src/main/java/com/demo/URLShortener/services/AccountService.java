package com.demo.URLShortener.services;

import com.demo.URLShortener.domain.Account;

public interface AccountService {

    Boolean checkIfAccountExists(String accountId);
    Account registerAccount(Account account);
}
