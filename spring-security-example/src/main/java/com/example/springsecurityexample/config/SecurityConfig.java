package com.example.springsecurityexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .httpBasic()
                .and().authorizeHttpRequests()
                //.anyRequest().permitAll()
                //.anyRequest().denyAll()
                .anyRequest().authenticated()
                //.anyRequest().hasRole("ADMIN")
                //.anyRequest().hasAuthority("ADMIN")
                //.requestMatchers("/admin").permitAll()
                //.requestMatchers(HttpMethod.GET).permitAll()
                //.requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                //.requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
