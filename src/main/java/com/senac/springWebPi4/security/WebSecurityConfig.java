package com.senac.springWebPi4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()).logout(logout -> logout.logoutUrl("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails user =
                User.builder()
                        .username("joão")
                        .password(encoder.encode("joao"))
                        .roles("USER")
                        .build();
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder)
                .withUser(user);
    }
}
