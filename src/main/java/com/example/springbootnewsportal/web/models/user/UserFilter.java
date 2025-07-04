package com.example.springbootnewsportal.web.models.user;
import com.example.springbootnewsportal.validation.user.UserFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@UserFilterValid
@Data
@NoArgsConstructor
public class UserFilter {

    private Integer pageNumber;

    private Integer pageSize;
}