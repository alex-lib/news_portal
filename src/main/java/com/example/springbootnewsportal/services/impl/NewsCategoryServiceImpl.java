package com.example.springbootnewsportal.services.impl;

import com.example.springbootnewsportal.entities.NewsCategory;
import com.example.springbootnewsportal.exceptions.EntityNotFoundException;
import com.example.springbootnewsportal.repositories.NewsCategoryRepository;
import com.example.springbootnewsportal.services.NewsCategoryService;
import com.example.springbootnewsportal.utils.BeanUtils;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsCategory> filterBy(NewsCategoryFilter newsCategoryFilter) {
        return newsCategoryRepository.findAll(PageRequest.of(newsCategoryFilter.getPageNumber(),
                newsCategoryFilter.getPageSize())).getContent();
    }

    @Override
    public NewsCategory update(NewsCategory newsCategory, Long id) {
        NewsCategory existedNewsCategory = findById(newsCategory.getId());
        BeanUtils.copyNonNullProperties(newsCategory, existedNewsCategory);
        return newsCategoryRepository.save(existedNewsCategory);
    }

    @Override
    public void deleteById(Long id) {
        newsCategoryRepository.deleteById(id);
    }

    @Override
    public NewsCategory findByNameNewsCategory(String nameNewsCategory) {
        return newsCategoryRepository.findByNameNewsCategory(nameNewsCategory).orElseThrow(() -> new EntityNotFoundException(MessageFormat
                .format("Category with name {0} is not existed", nameNewsCategory)));
    }

    @Transactional
    @Override
    public NewsCategory create(NewsCategory newsCategory) {
        if (newsCategoryRepository.findByNameNewsCategory(newsCategory.getNameNewsCategory()).isPresent()) return newsCategory;
        return newsCategoryRepository.save(newsCategory);
    }

    @Override
    public NewsCategory findById(Long id) {
        return newsCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat
                .format("News with id %d is not found", id)));
    }
}