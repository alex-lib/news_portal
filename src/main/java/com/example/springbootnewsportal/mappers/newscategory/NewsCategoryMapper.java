package com.example.springbootnewsportal.mappers.newscategory;

import com.example.springbootnewsportal.entities.NewsCategory;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryListResponse;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryRequest;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsCategoryMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsCategoryMapper {
    NewsCategory requestToNewsCategory(NewsCategoryRequest newsCategoryRequest);

    @Mapping(source = "newsCategoryId", target = "id")
    NewsCategory requestToNewsCategory(Long newsCategoryId, NewsCategoryRequest newsCategoryRequest);

    NewsCategoryResponse newsCategoryToResponse(NewsCategory newsCategory);

    default NewsCategoryListResponse newsCategoryListToNewsCategoryListResponse(List<NewsCategory> newsCategories) {
        NewsCategoryListResponse newsCategoryListResponse = new NewsCategoryListResponse();
        newsCategoryListResponse.setNewsCategories(newsCategories.stream().map(this::newsCategoryToResponse).collect(Collectors.toList()));
        return newsCategoryListResponse;
    }
}