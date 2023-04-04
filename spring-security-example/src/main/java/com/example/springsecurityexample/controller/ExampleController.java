package com.example.springsecurityexample.controller;

import com.example.springsecurityexample.entity.User;
import com.example.springsecurityexample.service.SecurityUserDetailsService;
import com.example.springsecurityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ExampleController {

    @Autowired
    private UserService userService;

    @GetMapping("/demo")
    public String demo(){
        return "this is a demo";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        var users = userService.getUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }
}
