package com.example.springbootnewsportal.services.impl;
import com.example.springbootnewsportal.exceptions.EntityNotFoundException;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.repositories.UserRepository;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import com.example.springbootnewsportal.web.models.user.UserFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(existedUser);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}