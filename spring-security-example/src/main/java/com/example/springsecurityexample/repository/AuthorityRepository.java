package com.example.springsecurityexample.repository;

import com.example.springsecurityexample.entity.Authority;
import com.example.springsecurityexample.utils.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long > {

    Optional<Authority> findByRol(AuthorityName authorityName);
}
