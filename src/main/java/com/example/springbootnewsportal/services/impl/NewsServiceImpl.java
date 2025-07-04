package com.example.springbootnewsportal.services.impl;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.exceptions.EntityNotFoundException;
import com.example.springbootnewsportal.repositories.NewsRepository;
import com.example.springbootnewsportal.services.NewsService;
import com.example.springbootnewsportal.services.NewsSpecification;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import com.example.springbootnewsportal.web.models.news.NewsFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;

    @Override
    public List<News> filterBy(NewsFilter newsFilter) {
        return newsRepository.findAll(NewsSpecification.withFilter(newsFilter),
                PageRequest.of(newsFilter.getPageNumber(),
                        newsFilter.getPageSize())).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                    .format("News with id %d is not found", id)));
    }

    @Override
    public News create(News news) {
        return newsRepository.save(news);
    }

    @Transactional
    @Override
    public News update(News news, String userPassword) {
        User user = userService.findById(news.getUserCreatorNews().getId());
        News existedNews = findById(news.getId());
        BeanUtils.copyNonNullProperties(news, existedNews);
        existedNews.setUserCreatorNews(user);
        return newsRepository.save(existedNews);
    }

    @Transactional
    @Override
    public void deleteById(Long id, String userPassword) {
        newsRepository.deleteById(id);
    }
}