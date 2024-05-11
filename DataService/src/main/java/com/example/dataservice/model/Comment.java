package com.example.dataservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;

    @Column(name = "like_number", nullable = false)
    private Integer likeNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    public Comment(Integer id, String text, Integer likeNumber, News news) {
        this.id = id;
        this.text = text;
        this.likeNumber = likeNumber;
        this.news = news;
    }

    public Comment() {

    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", likeNumber=" + likeNumber +
                ", news=" + news +
                '}';
    }
}