package com.demo.URLShortener.repositories;

import com.demo.URLShortener.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
