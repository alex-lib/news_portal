package com.example.springbootnewsportal.web.models.newscategory;
import com.example.springbootnewsportal.validation.newscategory.NewsCategoryFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@NewsCategoryFilterValid
@Data
@NoArgsConstructor
public class NewsCategoryFilter {

    private Integer pageNumber;

    private Integer pageSize;
}