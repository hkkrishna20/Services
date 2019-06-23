package com.cgi.ewi.token.oauth.repository;

import org.springframework.data.repository.CrudRepository;

import com.cgi.ewi.token.oauth.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
