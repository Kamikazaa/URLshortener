package com.demo.URLShortener.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table (name = "websites")
public class RegisterUrl {

    @Id
    @Column( name = "short_url")
    private String shortUrl;
    @Column ( name = "url")
    private String url;
    @Column (name = "account_id")
    private String account;
    @Column (name = "redirect_type")
    private String redirectType;
    @Column (name = "count")
    private Integer count;

    @Override
    public String toString() {
        return "RegisterUrl{" +
                "shortUrl='" + shortUrl + '\'' +
                ", url='" + url + '\'' +
                ", account='" + account + '\'' +
                ", redirectType='" + redirectType + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String shortUrlJson() {
        return "{" +
                "\"shortUrl\": \"http://localhost:8080/" + shortUrl + '"' +
                '}';
    }
}
