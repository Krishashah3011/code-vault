package com.codevault.snippets.service;

import java.util.List;
import com.codevault.snippets.entity.User;

public interface UserService {

    User findByUsername(String username);
    User saveUser(User user);
    List<User> getAllUsers();
}