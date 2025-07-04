package com.example.springbootnewsportal.validation.user;
import com.example.springbootnewsportal.web.models.user.UserFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class UserFilterValidValidator implements ConstraintValidator<UserFilterValid, UserFilter> {

    @Override
    public boolean isValid(UserFilter userFilter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(userFilter.getPageNumber(), userFilter.getPageSize());
    }
}