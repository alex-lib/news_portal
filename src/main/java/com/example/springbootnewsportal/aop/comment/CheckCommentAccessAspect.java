package com.example.springbootnewsportal.aop.comment;
import com.example.springbootnewsportal.exceptions.CommentAccessException;
import com.example.springbootnewsportal.services.CommentService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckCommentAccessAspect {

    @Autowired
    private CommentService commentService;

    @Before("@annotation(CheckCommentAccess) && args(id, password, ..)")
    public void checkAccess(Long id, String password) {

        if (!commentService.findById(id).getUserCreatorComment().getPassword().equals(password)) {
            throw new CommentAccessException("Impossible to update/delete comment because password is wrong");
        }
    }
}