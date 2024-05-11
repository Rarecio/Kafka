package com.example.dataservice.service;

import com.example.dataservice.model.Comment;
import com.example.dataservice.model.News;
import com.example.dataservice.repository.CommentRepository;
import com.example.dataservice.repository.NewsRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    public DataService(NewsRepository newsRepository, CommentRepository commentRepository) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
    }

    public News findNewsById(int id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("не существует новости с id: " + id));
    }

    public Comment findCommentById(int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("не существует комментария с id: " + id));
    }

    public List<Comment> findCommentsByNewsId(int id) {
        return commentRepository.findAllByNewsId(id);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public void saveNews(News news) {
        newsRepository.save(news);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
