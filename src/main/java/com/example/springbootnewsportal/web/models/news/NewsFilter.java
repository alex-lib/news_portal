package com.example.springbootnewsportal.web.models.news;
import com.example.springbootnewsportal.validation.news.NewsFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@NewsFilterValid
@Data
@NoArgsConstructor
public class NewsFilter {

    private String title;

    private Integer pageNumber;

    private Integer pageSize;

    private String userCreatorNewsName;

    private String categoryName;

    private Instant createdBefore;

    private Instant updatedBefore;

    private Long userCreatorNewsId;
}