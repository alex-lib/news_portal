package com.example.springbootnewsportal.web.models.news;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsRequest {

    @NotBlank(message = "Title must be provided")
    private String title;

    @NotBlank(message = "Content must be provided")
    private String content;

    @NotBlank(message = "Category must be provided")
    private String newsCategory;
}