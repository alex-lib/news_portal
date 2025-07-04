package com.example.springbootnewsportal.web.models.newscategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsCategoryListResponse {

    List<NewsCategoryResponse> newsCategories = new ArrayList<>();
}
