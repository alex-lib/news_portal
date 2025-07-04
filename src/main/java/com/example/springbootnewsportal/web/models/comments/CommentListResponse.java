package com.example.springbootnewsportal.web.models.comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentListResponse {

    @Builder.Default
    private List<CommentResponse> comments = new ArrayList<>();
}