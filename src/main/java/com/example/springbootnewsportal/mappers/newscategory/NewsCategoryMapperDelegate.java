package com.example.springbootnewsportal.mappers.newscategory;
import com.example.springbootnewsportal.entities.NewsCategory;
import com.example.springbootnewsportal.services.UserService;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsCategoryMapperDelegate implements NewsCategoryMapper {

    @Autowired
    private UserService userService;

    @Override
    public NewsCategory requestToNewsCategory(NewsCategoryRequest newsCategoryRequest) {
        NewsCategory newsCategory = new NewsCategory();
        newsCategory.setNameNewsCategory(newsCategoryRequest.getNameNewsCategory());
        newsCategory.setUserCreatorNewsCategory(userService.findById(newsCategoryRequest.getUserId()));
        return newsCategory;
    }

    @Override
    public NewsCategory requestToNewsCategory(Long newsCategoryId, NewsCategoryRequest newsCategoryRequest) {
        NewsCategory newsCategory = requestToNewsCategory(newsCategoryRequest);
        newsCategory.setId(newsCategoryId);
        return newsCategory;
    }
}