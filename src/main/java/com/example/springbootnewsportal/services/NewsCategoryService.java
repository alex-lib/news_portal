package com.example.springbootnewsportal.services;
import com.example.springbootnewsportal.entities.NewsCategory;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryFilter;
import java.util.List;

public interface NewsCategoryService {

    NewsCategory findByNameNewsCategory(String nameNewsCategory);

    NewsCategory create(NewsCategory newsCategory);

    NewsCategory findById(Long id);

    List<NewsCategory> filterBy(NewsCategoryFilter newsCategoryFilter);
}