package com.demo.URLShortener.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;
    @Transient
    @Column(name = "password")
    private String password;
    @Column (name = "encrypted_password")
    private String encryptedPassword;
    @Column (name = "role")
    private String role;
    @Column(name = "success")
    private Boolean success;
    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private List<RegisterUrl> registerUrls;

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", password='" + password + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", role='" + role + '\'' +
                ", success=" + success +
                ", description='" + description + '\'' +
                ", registerUrls=" + registerUrls +
                '}';
    }

    public String toStringSuccessfulRegistration() {
        return "{" +
                "\"success\":" + success +
                ", \"description\":\"" + description + '"' +
                ", \"password\":\"" + password + '"' +
                "}";
    }

    public String toStringFailedRegistration() {
        return "{" +
                "\"success\":" + success +
                ", \"description\":\"" + description + '"' +
                "}";
    }
}


