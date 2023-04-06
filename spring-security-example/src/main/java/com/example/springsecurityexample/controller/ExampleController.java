package com.example.springsecurityexample.controller;

import com.example.springsecurityexample.entity.User;
import com.example.springsecurityexample.service.SecurityUserDetailsService;
import com.example.springsecurityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExampleController {

    @Autowired
    private UserService userService;

    @GetMapping("/demo/{param}")
    @PreAuthorize("@conditionEvaluator.canGetUser(#param, authentication)")
    public String demo(@PathVariable String param){
        return "this is a demo for show the annotation PreAuthorize";
    }

    @GetMapping("/users")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getUsers(){
        var users = userService.getUser();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }
}
