package com.example.springbootnewsportal.web.controllers;
import com.example.springbootnewsportal.aop.comment.CheckCommentAccess;
import com.example.springbootnewsportal.entities.Comment;
import com.example.springbootnewsportal.mappers.comment.CommentMapper;
import com.example.springbootnewsportal.services.CommentService;
import com.example.springbootnewsportal.web.models.comments.CommentListResponse;
import com.example.springbootnewsportal.web.models.comments.CommentRequest;
import com.example.springbootnewsportal.web.models.comments.CommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping("/{newsId}")
    public ResponseEntity<CommentListResponse> findAllByNews(@PathVariable Long newsId) {
        return ResponseEntity.ok(commentMapper.commentListToCommentListResponse(commentService.findAllByNewsId(newsId)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest commentRequest) {
        Comment newComment = commentService.create(commentMapper.requestToComment(commentRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToResponse(newComment));
    }

    @CheckCommentAccess
    @PutMapping("/{id}/{password}")
    public ResponseEntity<CommentResponse> update(@PathVariable("id") Long id, @PathVariable("password") String password ,@RequestBody @Valid CommentRequest commentRequest) {
        Comment updatedComment = commentService.update(commentMapper.requestToComment(id, commentRequest), password);
        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }

    @CheckCommentAccess
    @DeleteMapping("/{id}/{password}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, @PathVariable("password") String password) {
        commentService.deleteById(id, password);
        return ResponseEntity.noContent().build();
    }
}