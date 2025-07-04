package com.example.springbootnewsportal.web.controllers;
import com.example.springbootnewsportal.aop.news.CheckNewsAccess;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.mappers.news.NewsMapper;
import com.example.springbootnewsportal.services.NewsService;
import com.example.springbootnewsportal.web.models.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping("/find-all-by-filter")
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter newsFilter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.filterBy(newsFilter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseForOneNews> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsMapper.newsToResponseForOneNews(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest newsRequest) {
        News newNews = newsService.create(newsMapper.requestToNews(newsRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(newNews));
    }

    @CheckNewsAccess
    @PutMapping("/{id}/{password}")
    public ResponseEntity<NewsResponse> update(@PathVariable("id") Long id, @PathVariable("password") String password ,@RequestBody @Valid NewsRequest newsRequest) {
        News updatedNews = newsService.update(newsMapper.requestToNews(id, newsRequest), password);
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @CheckNewsAccess
    @DeleteMapping("/{id}/{password}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, @PathVariable("password") String password) {
        newsService.deleteById(id, password);
        return ResponseEntity.noContent().build();
    }
}