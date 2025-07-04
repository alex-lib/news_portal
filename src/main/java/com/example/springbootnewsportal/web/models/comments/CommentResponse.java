package com.example.springbootnewsportal.web.models.comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponse {

    private String comment;

    private String userCreatorComment;
}