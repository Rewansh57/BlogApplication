package com.example.own.blogApp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    @Column(length = 10000)
    private String content;
    private int likes = 0;
    @OneToMany(mappedBy="post",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public int getLikes() {
        return likes;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
