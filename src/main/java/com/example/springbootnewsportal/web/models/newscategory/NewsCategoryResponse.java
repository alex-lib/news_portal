package com.example.springbootnewsportal.web.models.newscategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryResponse {

   private Long id;

   private String nameNewsCategory;
}