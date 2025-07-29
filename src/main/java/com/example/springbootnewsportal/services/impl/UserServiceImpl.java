package com.example.springbootnewsportal.services.impl;

import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.entities.roles.Role;
import com.example.springbootnewsportal.entities.roles.RoleType;
import com.example.springbootnewsportal.exceptions.EntityNotFoundException;
import com.example.springbootnewsportal.repositories.UserRepository;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import com.example.springbootnewsportal.web.models.user.UserFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> filterBy(UserFilter userFilter) {
        return userRepository.findAll(PageRequest.of(userFilter.getPageNumber(),
                        userFilter.getPageSize())).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                    .format("User with id %d is not found", id)));
    }

    @Transactional
    @Override
    public User create(User user, RoleType roleType) {
        Role role = Role.from(roleType);
        role.setUser(user);
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(existedUser);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username).orElseThrow(() -> new EntityNotFoundException("There is no such user"));
    }
}