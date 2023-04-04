package com.example.springsecurityexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(){
//        var passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new InMemoryUserDetailsManager(
//                User.withUsername("titi")
//                        .password("123")
//                        .roles("USER")
//                        .build(),
//                User.withUsername("admin")
//                        .password("321")
//                        .roles("ADMIN")
//                        .build()
//        );
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .httpBasic()
                .and().authorizeHttpRequests()
                //.anyRequest().permitAll()
                //.anyRequest().denyAll()
                //.anyRequest().authenticated()
                //.anyRequest().hasRole("ADMIN")
                //.anyRequest().hasAuthority("ADMIN")
                //.requestMatchers("/admin").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
