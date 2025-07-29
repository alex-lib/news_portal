package com.example.springbootnewsportal.web.controllers;
import com.example.springbootnewsportal.aop.news.CheckDeleteNewsAccess;
import com.example.springbootnewsportal.aop.news.CheckUpdateNewsAccess;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.mappers.news.NewsMapper;
import com.example.springbootnewsportal.services.NewsService;
import com.example.springbootnewsportal.web.models.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @GetMapping("/find-all-by-filter")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter newsFilter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.filterBy(newsFilter)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsResponseForOneNews> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsMapper.newsToResponseForOneNews(newsService.findById(id)));
    }

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest newsRequest) {
        News newNews = newsService.create(newsMapper.requestToNews(newsRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(newNews));
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @CheckUpdateNewsAccess
    public ResponseEntity<NewsResponse> update(@PathVariable("id") Long id, @RequestBody @Valid NewsRequest newsRequest) {
        News updatedNews = newsService.update(newsMapper.requestToNews(id, newsRequest));
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    @CheckDeleteNewsAccess
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}