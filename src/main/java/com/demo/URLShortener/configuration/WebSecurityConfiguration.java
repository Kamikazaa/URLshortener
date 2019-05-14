package com.demo.URLShortener.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public WebSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("select ACCOUNT_ID, ROLE from ACCOUNT where ACCOUNT_ID=?")
                .usersByUsernameQuery("select ACCOUNT_ID, ENCRYPTED_PASSWORD, 1 as enabled from ACCOUNT where ACCOUNT_ID=?");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
            .authorizeRequests().antMatchers("h2-console/**").permitAll().and()
            .authorizeRequests().antMatchers("help").permitAll();

        httpSecurity.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/api/v1/URLshortener/account/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .csrf().disable();

        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

}
