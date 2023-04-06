package com.example.springsecurityexample.service;

import com.example.springsecurityexample.entity.User;
import com.example.springsecurityexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User addUser(User user){
        var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoders.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
