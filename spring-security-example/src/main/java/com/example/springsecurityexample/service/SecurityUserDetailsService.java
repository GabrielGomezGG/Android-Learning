package com.example.springsecurityexample.service;

import com.example.springsecurityexample.entity.User;
import com.example.springsecurityexample.repository.UserRepository;
import com.example.springsecurityexample.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return new SecurityUser(user.get());
        }

        throw new UsernameNotFoundException("usuario no encontrado: " + username);
    }
}
