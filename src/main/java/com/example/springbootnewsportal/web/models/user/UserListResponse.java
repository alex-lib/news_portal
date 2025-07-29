package com.example.springbootnewsportal.web.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserListResponse {

    private List<UserResponse> users = new ArrayList<>();
}