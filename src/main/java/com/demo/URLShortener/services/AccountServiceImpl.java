package com.demo.URLShortener.services;

import com.demo.URLShortener.domain.Account;
import com.demo.URLShortener.repositories.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.URLShortener.utilities.RandomStringGenerator;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;



    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean checkIfAccountExists(String accountId) {
        return accountRepository.existsById(accountId);
    }

    @Override
    public Account registerAccount(Account account) {

        if (checkIfAccountExists(account.getAccountId())) {
            account.setDescription("User already exists");
            account.setSuccess(false);
        }
        else {

            String password = RandomStringGenerator.generateRandomString(8);

            account.setPassword(password);
            account.setEncryptedPassword(passwordEncoder.encode(password));
            account.setRole("ADMIN");
            account.setSuccess(true);
            account.setDescription("User account created with account ID:" + account.getAccountId());
            accountRepository.save(account);
        }

        return account;
    }
}
