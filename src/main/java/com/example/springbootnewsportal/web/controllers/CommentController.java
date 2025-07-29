package com.example.springbootnewsportal.web.controllers;
import com.example.springbootnewsportal.aop.comment.CheckDeleteCommentAccess;
import com.example.springbootnewsportal.aop.comment.CheckUpdateCommentAccess;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping("/{newsId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<CommentListResponse> findAllByNews(@PathVariable Long newsId) {
        return ResponseEntity.ok(commentMapper.commentListToCommentListResponse(commentService.findAllByNewsId(newsId)));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest commentRequest) {
        Comment newComment = commentService.create(commentMapper.requestToComment(commentRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToResponse(newComment));
    }

    @CheckUpdateCommentAccess
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CommentResponse> update(@PathVariable("id") Long id, @RequestBody @Valid CommentRequest commentRequest) {
        Comment updatedComment = commentService.update(commentMapper.requestToComment(id, commentRequest));
        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }

    @CheckDeleteCommentAccess
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}