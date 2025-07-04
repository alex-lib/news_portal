package com.example.springbootnewsportal.mappers.user;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.web.models.user.UserRequest;

public abstract class UserMapperDelegate implements UserMapper {

    @Override
    public User requestToUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    @Override
    public User requestToUser(Long userId, UserRequest userRequest) {
        User user = requestToUser(userRequest);
        user.setId(userId);
        return user;
    }
}