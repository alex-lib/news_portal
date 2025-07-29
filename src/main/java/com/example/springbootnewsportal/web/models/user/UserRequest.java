package com.example.springbootnewsportal.web.models.user;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    @NotBlank(message = "Name must be specified")
    @Size(min = 2, max = 20, message = "Name must contain between {min} and {max} characters")
    private String name;

    @NotBlank(message = "Password must be specified")
    @Size(min = 4, max = 10, message = "Password must contain between {min} and {max} characters")
    private String password;
}