package com.example.springbootnewsportal.web.models.newscategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsCategoryRequest {

    @NotBlank(message = "Category's name must be provided")
    private String nameNewsCategory;
}