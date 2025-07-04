package com.example.springbootnewsportal.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity(name = "news_categories")
public class NewsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "name_news_category")
    private String nameNewsCategory;

    @OneToMany(mappedBy = "newsCategory", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<News> news = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_creator_news_category_id")
    @ToString.Exclude
    private User userCreatorNewsCategory;
}