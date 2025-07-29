package com.example.springbootnewsportal.web.models.news;

import com.example.springbootnewsportal.web.models.comments.CommentResponse;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewsResponseForOneNews {

    private Long id;

    private String title;

    private String content;

    private String newsCategory;

    @Builder.Default
    private List<CommentResponse> comments = new ArrayList<>();

    @Nullable
    private int commentsCount;

}