package com.example.springbootnewsportal.services;

import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.entities.roles.RoleType;
import com.example.springbootnewsportal.web.models.user.UserFilter;

import java.util.List;

public interface UserService {

    List<User> filterBy(UserFilter userFilter);

    User findById(Long id);

    User create(User user, RoleType roleType);

    User update(User user);

    void deleteById(Long id);

    User findByName(String username);
}