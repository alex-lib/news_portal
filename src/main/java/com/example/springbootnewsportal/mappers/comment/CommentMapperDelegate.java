package com.example.springbootnewsportal.mappers.comment;
import com.example.springbootnewsportal.entities.Comment;
import com.example.springbootnewsportal.security.AuthenticationService;
import com.example.springbootnewsportal.services.NewsService;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.web.models.comments.CommentRequest;
import com.example.springbootnewsportal.web.models.comments.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Comment requestToComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setComment(commentRequest.getComment());
        comment.setUserCreatorComment(userService.findById(authenticationService.getCurrentUser().getId()));
        comment.setNews(newsService.findById(commentRequest.getNewsId()));
        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, CommentRequest commentRequest) {
        Comment comment = requestToComment(commentRequest);
        comment.setId(commentId);
        return comment;
    }

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComment(comment.getComment());
        commentResponse.setUserCreatorComment(comment.getUserCreatorComment().getName());
        return commentResponse;
    }
}