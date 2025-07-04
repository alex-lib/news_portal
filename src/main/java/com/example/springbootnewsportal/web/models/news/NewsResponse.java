package com.example.springbootnewsportal.web.models.news;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsResponse {

    private Long id;

    private String title;

    private String content;

    @Nullable
    private Integer commentsCount;

    private String newsCategory;
}