package com.example.springbootnewsportal.mappers.news;

import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.entities.NewsCategory;
import com.example.springbootnewsportal.entities.User;
import com.example.springbootnewsportal.web.models.news.NewsListResponse;
import com.example.springbootnewsportal.web.models.news.NewsRequest;
import com.example.springbootnewsportal.web.models.news.NewsResponse;
import com.example.springbootnewsportal.web.models.news.NewsResponseForOneNews;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {

    News requestToNews(NewsRequest newsRequest);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, NewsRequest newsRequest);

    NewsResponse newsToResponse(News news);

    default List<NewsResponse> newsListToResponses(List<News> news) {
        List<NewsResponse> list = new ArrayList<>();
        for (News oneNews : news) {
            NewsResponse newsResponse = new NewsResponse();
            newsResponse.setId(oneNews.getId());
            newsResponse.setTitle(oneNews.getTitle());
            newsResponse.setContent(oneNews.getContent());
            newsResponse.setCommentsCount(oneNews.getCommentsCount());
            newsResponse.setNewsCategory(oneNews.getNewsCategory().getNameNewsCategory());
            list.add(newsResponse);
        }
        return list;
    }

    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        NewsListResponse newsListResponse = new NewsListResponse();
        newsListResponse.setNews(newsListToResponses(news));
        return newsListResponse;
    }

    NewsResponseForOneNews newsToResponseForOneNews(News news);

    default NewsCategory map(String value) {
        NewsCategory newsCategory = new NewsCategory();
        newsCategory.setNameNewsCategory(value);
        return newsCategory;
    }

    default String map(NewsCategory value) {
        return value.getNameNewsCategory();
    }

    default String map(User user) {
        return user.getName();
    }
}