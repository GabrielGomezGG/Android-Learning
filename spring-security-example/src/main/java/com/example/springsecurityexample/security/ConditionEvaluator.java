package com.example.springsecurityexample.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ConditionEvaluator {

    public boolean canGetUser(String param, Authentication authentication){
        return param.equals(authentication.getName()) && authentication
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }
}
