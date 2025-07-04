package com.example.springbootnewsportal.web.models.news;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsRequest {

    @NotNull(message = "User's ID must be provided")
    @Positive(message = "User's ID must be more than 0")
    private Long userId;

    @NotBlank(message = "Title must be provided")
    private String title;

    @NotBlank(message = "Content must be provided")
    private String content;

    @NotBlank(message = "Category must be provided")
    private String newsCategory;
}