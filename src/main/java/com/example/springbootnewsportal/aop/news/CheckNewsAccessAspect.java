package com.example.springbootnewsportal.aop.news;
import com.example.springbootnewsportal.exceptions.CommentAccessException;
import com.example.springbootnewsportal.services.NewsService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckNewsAccessAspect {

    @Autowired
    private NewsService newsService;

    @Before("@annotation(CheckNewsAccess ) && args(id, password, ..)")
    public void checkAccess(Long id, String password) {

        if (!newsService.findById(id).getUserCreatorNews().getPassword().equals(password)) {
            throw new CommentAccessException("Impossible to update/delete news because password is wrong");
        }
    }
}