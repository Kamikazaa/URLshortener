package com.demo.URLShortener.repositories;

import com.demo.URLShortener.domain.RegisterUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RegisterUrlRepository extends JpaRepository<RegisterUrl, String> {

    @Query ("select r.url from RegisterUrl r where r.url=?1 and r.account=?2")
    String checkLongUrl (String url, String user);

    @Query ("select r.url from RegisterUrl r where r.shortUrl=?1")
    String getLongUrl (String shortUrl);

    @Query ("select r.redirectType from RegisterUrl r where r.shortUrl=?1")
    String getRedirectType (String shortUrl);

    @Query ("select r.shortUrl from RegisterUrl r where r.shortUrl=?1")
    String checkShortUrl (String shortUrl);

    @Transactional
    @Modifying
    @Query ("update RegisterUrl r set r.count = r.count + 1 where r.shortUrl=?1")
    void updateCount(String shortUrl);

    List<RegisterUrl> findByAccount(String accountId);

    //@Query ("select r.url, r.count from RegisterUrl r where r.account=?1")
    //List<String> getStatistics (String user);

}
