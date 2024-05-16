package com.example.dataservice.service;

import com.example.dataservice.mapper.NewsMapper;
import com.example.dataservice.model.Comment;
import com.example.dataservice.model.News;
import com.example.dataservice.model.NewsWithNumber;
import com.example.dataservice.repository.CommentRepository;
import com.example.dataservice.repository.NewsRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    @Autowired
    private NewsMapper newsMapper;

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

    public List<NewsWithNumber> findTop4NewsWithCommentCount(){
        List<Comment> list = commentRepository.findAll();
        Map<Integer, Long> map = list.stream()
                .collect(Collectors.groupingBy(e->e.getNews().getId(), Collectors.counting()));
        List<NewsWithNumber> res = map.keySet().stream()
                .map(e -> new NewsWithNumber(e, map.get(e)))
                .sorted(Comparator.comparingLong(NewsWithNumber::value))
                .limit(2)
                .toList();
        return res;
    }

    public List<NewsWithNumber> findTopWithLikeNumber(){
        List<Comment> list = commentRepository.findAll();
        Map<Integer, Long> map = list.stream()
                .collect(Collectors.groupingBy(e->e.getNews().getId(), Collectors.summingLong(Comment::getLikeNumber)));
        List<NewsWithNumber> res = map.keySet().stream()
                .map(e -> new NewsWithNumber(e, map.get(e)))
                .sorted(Comparator.comparingLong(NewsWithNumber::value))
                .limit(3)
                .toList();
        return res;
    }
}
