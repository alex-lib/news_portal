package com.example.springbootnewsportal.web.controllers;
import com.example.springbootnewsportal.aop.user.CheckUserAccess;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.entities.roles.RoleType;
import com.example.springbootnewsportal.mappers.user.UserMapper;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.web.models.user.UserFilter;
import com.example.springbootnewsportal.web.models.user.UserListResponse;
import com.example.springbootnewsportal.web.models.user.UserRequest;
import com.example.springbootnewsportal.web.models.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/find-all-by-filter")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserListResponse> findAll(@Valid UserFilter userFilter) {
        return ResponseEntity.ok(userMapper.userListToUserListResponse(userService.filterBy(userFilter)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckUserAccess
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest, @RequestParam RoleType roleType) {
        User newUser = userService.create(userMapper.requestToUser(userRequest), roleType);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckUserAccess
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody @Valid UserRequest userRequest) {
        User updatedUser = userService.update(userMapper.requestToUser(id, userRequest));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckUserAccess
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}