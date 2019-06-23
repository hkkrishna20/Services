package com.cgi.springoauth2example.service;

import java.util.List;

import com.cgi.springoauth2example.model.User;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}