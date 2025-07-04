package com.example.springbootnewsportal.web.models.comments;
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
public class CommentRequest {

    @NotNull(message = "User's ID must be provided")
    @Positive(message = "User's ID must be more than 0")
    private Long userId;

    @NotBlank(message = "Comment must be provided")
    private String comment;

    @NotNull(message = "News's ID must be provided")
    @Positive(message = "News's ID must be more than 0")
    private Long newsId;
}