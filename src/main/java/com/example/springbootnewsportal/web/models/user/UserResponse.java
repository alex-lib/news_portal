package com.example.springbootnewsportal.web.models.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    private Long id;

    private String name;

    private String password;
}