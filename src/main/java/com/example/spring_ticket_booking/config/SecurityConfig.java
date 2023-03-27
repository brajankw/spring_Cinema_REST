package com.example.spring_ticket_booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(DataSource dataSource, CustomUserDetailsService customUserDetailsService) {
        this.dataSource = dataSource;
        this.customUserDetailsService = customUserDetailsService;
    }

    public UserDetailsManager userDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/movies").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/movies/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/purchase").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/account").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/return").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/seats").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/rooms").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/rooms").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/movies").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/stats").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/roles/**").hasRole("ADMIN")
        );

        http.httpBasic();

        http.csrf().disable();

        return http.build();
    }


}

