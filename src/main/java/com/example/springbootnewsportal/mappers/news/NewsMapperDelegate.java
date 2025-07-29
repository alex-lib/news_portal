package com.example.springbootnewsportal.mappers.news;

import com.example.springbootnewsportal.entities.Comment;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.security.AuthenticationService;
import com.example.springbootnewsportal.services.CommentService;
import com.example.springbootnewsportal.services.NewsCategoryService;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.web.models.comments.CommentResponse;
import com.example.springbootnewsportal.web.models.news.NewsRequest;
import com.example.springbootnewsportal.web.models.news.NewsResponse;
import com.example.springbootnewsportal.web.models.news.NewsResponseForOneNews;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class NewsMapperDelegate implements NewsMapper {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Override
    public News requestToNews(NewsRequest newsRequest) {
        News news = new News();
        news.setTitle(newsRequest.getTitle());
        news.setContent(newsRequest.getContent());
        news.setUserCreatorNews(userService.findById(authenticationService.getCurrentUser().getId()));
        news.setNewsCategory(newsCategoryService.findByNameNewsCategory(newsRequest.getNewsCategory()));
        return news;
    }

    @Override
    public News requestToNews(Long newsId, NewsRequest newsRequest) {
        News news = requestToNews(newsRequest);
        news.setId(newsId);
        return news;
    }

    @Override
    public NewsResponseForOneNews newsToResponseForOneNews(News news) {
        List<Comment> comments = commentService.findAllByNewsId(news.getId());
        List<CommentResponse> newsCommentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse newsComments = new CommentResponse();
            newsComments.setComment(comment.getComment());
            newsComments.setUserCreatorComment(comment.getUserCreatorComment().getName());
            newsCommentResponses.add(newsComments);
        }

        Integer commentsCount = news.getCommentsCount();
        if (commentsCount == null) {
            commentsCount = 0;
        }

        return NewsResponseForOneNews.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .commentsCount(commentsCount)
                .newsCategory(news.getNewsCategory().getNameNewsCategory())
                .comments(newsCommentResponses).build();
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setTitle(news.getTitle());
        newsResponse.setContent(news.getContent());
        Integer commentsCount = news.getCommentsCount();
        if (commentsCount == null) {
            commentsCount = 0;
        }
        newsResponse.setCommentsCount(commentsCount);
        newsResponse.setNewsCategory(news.getNewsCategory().getNameNewsCategory());
        return newsResponse;
    }
}