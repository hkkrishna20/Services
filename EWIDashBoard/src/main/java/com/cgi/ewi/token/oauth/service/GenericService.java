package com.cgi.ewi.token.oauth.service;

import java.util.List;

import com.cgi.ewi.token.oauth.domain.User;


public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

}
