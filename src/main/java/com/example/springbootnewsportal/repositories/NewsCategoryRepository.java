package com.example.springbootnewsportal.repositories;
import com.example.springbootnewsportal.entities.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long>, JpaSpecificationExecutor<NewsCategory> {

    @Query(value = "SELECT * FROM news_categories nc WHERE name_news_category = :nameNewsCategory", nativeQuery = true)
    Optional<NewsCategory> findByNameNewsCategory(String nameNewsCategory);
}