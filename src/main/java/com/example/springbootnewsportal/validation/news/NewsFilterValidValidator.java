package com.example.springbootnewsportal.validation.news;
import com.example.springbootnewsportal.web.models.news.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class NewsFilterValidValidator implements ConstraintValidator<NewsFilterValid, NewsFilter> {

    @Override
    public boolean isValid(NewsFilter newsFilter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(newsFilter.getPageNumber(), newsFilter.getPageSize());
    }
}