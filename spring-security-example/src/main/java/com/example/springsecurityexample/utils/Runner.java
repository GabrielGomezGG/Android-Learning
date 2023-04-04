package com.example.springsecurityexample.utils;

import com.example.springsecurityexample.entity.Authority;
import com.example.springsecurityexample.entity.User;
import com.example.springsecurityexample.repository.AuthorityRepository;
import com.example.springsecurityexample.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {

        if (this.authorityRepository.count() == 0) {
            this.authorityRepository.saveAll(List.of(
                    new Authority(AuthorityName.ADMIN),
                    new Authority(AuthorityName.READ),
                    new Authority(AuthorityName.WRITE)
            ));
        }

        if (this.userRepository.count() == 0) {
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            this.userRepository.saveAll(List.of(
                            new User("uncledave", encoders.encode("UncleDave123"), List.of(this.authorityRepository.findByRol(AuthorityName.ADMIN).get())),
                            new User("user01", "User01123", List.of(this.authorityRepository.findByRol(AuthorityName.READ).get())),
                            new User("user02", "User02123", List.of(this.authorityRepository.findByRol(AuthorityName.WRITE).get()))
                    )
            );
        }
    }
}
