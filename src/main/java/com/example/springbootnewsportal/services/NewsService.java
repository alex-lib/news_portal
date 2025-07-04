package com.example.springbootnewsportal.services;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.web.models.news.NewsFilter;
import java.util.List;

public interface NewsService {

    News findById(Long id);

    News create(News news);

    News update(News news, String userPassword);

    void deleteById(Long id, String userPassword);

    List<News> filterBy(NewsFilter NewsFilter);
}