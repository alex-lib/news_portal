package com.example.springbootnewsportal.services;
import com.example.springbootnewsportal.entities.Comment;
import java.util.List;

public interface CommentService {

    List<Comment> findAllByNewsId(Long newsId);

    Comment findById(Long id);

    Comment create(Comment comment);

    Comment update(Comment comment, String userPassword);

    void deleteById(Long id, String userPassword);
}