package com.example.springbootnewsportal.mappers.user;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.web.models.user.UserListResponse;
import com.example.springbootnewsportal.web.models.user.UserRequest;
import com.example.springbootnewsportal.web.models.user.UserResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(UserMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UserRequest userRequest);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UserRequest userRequest);

    UserResponse userToResponse(User User);

    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(users.stream().map(this::userToResponse).collect(Collectors.toList()));
        return userListResponse;
    }
}