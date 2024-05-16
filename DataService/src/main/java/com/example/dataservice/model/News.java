package com.example.dataservice.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;

    @Column(name = "like_number", nullable = false)
    private Integer likeNumber;

    public News(Integer id, String text, Integer likeNumber) {
        this.id = id;
        this.text = text;
        this.likeNumber = likeNumber;
    }

    public News() {

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

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public void incLikeNumber() {
        this.likeNumber++;
    }

    public void decLikeNumber() {
        if (likeNumber == 0) throw new IllegalCallerException("невозможно уменьшить количество лайков");
        this.likeNumber--;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", likeNumber=" + likeNumber +
                '}';
    }
}