package com.example.springbootnewsportal.web.controllers;
import com.example.springbootnewsportal.entities.NewsCategory;
import com.example.springbootnewsportal.mappers.newscategory.NewsCategoryMapper;
import com.example.springbootnewsportal.services.NewsCategoryService;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryFilter;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryListResponse;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryRequest;
import com.example.springbootnewsportal.web.models.newscategory.NewsCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/news-category")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;

    private final NewsCategoryMapper newsCategoryMapper;

    @GetMapping("/find-all-by-filter")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsCategoryListResponse> findAll(@Valid NewsCategoryFilter newsCategoryFilter) {
        return ResponseEntity.ok(newsCategoryMapper.newsCategoryListToNewsCategoryListResponse(newsCategoryService.filterBy(newsCategoryFilter)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsCategoryMapper.newsCategoryToResponse(newsCategoryService.findById(id)));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsCategoryResponse> create(@RequestBody NewsCategoryRequest newsCategoryRequest) {
        NewsCategory newNewsCategory = newsCategoryService.create(newsCategoryMapper.requestToNewsCategory(newsCategoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsCategoryMapper.newsCategoryToResponse(newNewsCategory));
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<NewsCategoryResponse> update(@PathVariable Long id, @RequestBody NewsCategoryRequest newsCategoryRequest) {
        NewsCategory updatedNewsCategory = newsCategoryService.update(newsCategoryMapper.requestToNewsCategory(id, newsCategoryRequest), id);
        return ResponseEntity.ok(newsCategoryMapper.newsCategoryToResponse(updatedNewsCategory));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}