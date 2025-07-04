package com.example.springbootnewsportal.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userCreatorComment", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "userCreatorNews", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<News> news = new ArrayList<>();

    @OneToMany(mappedBy = "userCreatorNewsCategory", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<NewsCategory> newsCategories = new ArrayList<>();
}