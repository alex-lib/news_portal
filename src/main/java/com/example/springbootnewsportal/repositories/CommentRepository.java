package com.example.springbootnewsportal.repositories;
import com.example.springbootnewsportal.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comments c WHERE c.news_id = :newsId", nativeQuery = true)
    List<Comment> findAllByNews(Long newsId);
}