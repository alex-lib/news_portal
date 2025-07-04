package com.example.springbootnewsportal.services;
import com.example.springbootnewsportal.entities.News;
import com.example.springbootnewsportal.web.models.news.NewsFilter;
import org.springframework.data.jpa.domain.Specification;
import java.time.Instant;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(byNewsTitle(newsFilter.getTitle()))
                .and(byUserCreatorNewsName(newsFilter.getUserCreatorNewsName()))
                .and(byCategoryName(newsFilter.getCategoryName()))
                .and(byCreatedBefore(newsFilter.getCreatedBefore()))
                .and(byUpdatedBefore(newsFilter.getUpdatedBefore()));
    }

    static Specification<News> byCategoryName(String categoryName) {
        return ((root, query, criteriaBuilder) -> {
            if (categoryName == null) return null;
            return criteriaBuilder.equal(root.get("newsCategory").get("nameNewsCategory"), categoryName);
        });
    }

    static Specification<News> byUserCreatorNewsName(String userCreatorNewsName) {
        return ((root, query, criteriaBuilder) -> {
            if (userCreatorNewsName == null) return null;
            return criteriaBuilder.equal(root.get("userCreatorNews").get("name"), userCreatorNewsName);
        });
    }

    static Specification<News> byUpdatedBefore(Instant updatedBefore) {
        return ((root, query, criteriaBuilder) -> {
            if (updatedBefore == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("updatedAt"), updatedBefore);
        });
    }

    static Specification<News> byCreatedBefore(Instant createdBefore) {
        return ((root, query, criteriaBuilder) -> {
            if (createdBefore == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdBefore);
        });
    }

    static Specification<News> byNewsTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null) return null;
            return criteriaBuilder.like(root.get("title"), title);
        };
    }
}