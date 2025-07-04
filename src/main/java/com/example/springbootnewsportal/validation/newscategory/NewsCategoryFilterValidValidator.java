package com.example.springbootnewsportal.validation.newscategory;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class NewsCategoryFilterValidValidator implements ConstraintValidator<NewsCategoryFilterValid, NewsCategoryFilter> {

    @Override
    public boolean isValid(NewsCategoryFilter newsCategoryFilter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(newsCategoryFilter.getPageNumber(), newsCategoryFilter.getPageSize());
    }
}