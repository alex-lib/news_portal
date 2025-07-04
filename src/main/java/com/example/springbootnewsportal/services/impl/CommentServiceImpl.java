package com.example.springbootnewsportal.services.impl;
import com.example.springbootnewsportal.entities.Comment;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.exceptions.EntityNotFoundException;
import com.example.springbootnewsportal.repositories.CommentRepository;
import com.example.springbootnewsportal.repositories.NewsRepository;
import com.example.springbootnewsportal.services.CommentService;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserService userService;

    @Override
    public List<Comment> findAllByNewsId(Long newsId) {
        return commentRepository.findAllByNews(newsId);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("News with id %d is not found", id)));
    }

    @Override
    public Comment create(Comment comment) {
        News news = comment.getNews();

        Integer commentsCount = news.getCommentsCount();
        if (commentsCount == null) {
            commentsCount = 0;
        }

        news.setCommentsCount(commentsCount + 1);
        newsRepository.save(news);
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment update(Comment comment, String userPassword) {
        User user = userService.findById(comment.getUserCreatorComment().getId());
        Comment existedComment = findById(comment.getId());
        BeanUtils.copyNonNullProperties(comment, existedComment);
        existedComment.setUserCreatorComment(user);
        return commentRepository.save(existedComment);
    }

    @Transactional
    @Override
    public void deleteById(Long id, String userPassword) {
        commentRepository.deleteById(id);
    }
}