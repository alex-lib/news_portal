package com.example.springbootnewsportal.web.models.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class NewsListResponse {

    private List<NewsResponse> news = new ArrayList<>();
}