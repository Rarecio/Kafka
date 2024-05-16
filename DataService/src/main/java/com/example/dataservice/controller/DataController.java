package com.example.dataservice.controller;

import com.example.dataservice.dto.CommentDTO;
import com.example.dataservice.dto.NewsDTO;
import com.example.dataservice.mapper.CommentMapper;
import com.example.dataservice.mapper.NewsMapper;
import com.example.dataservice.model.Comment;
import com.example.dataservice.model.News;
import com.example.dataservice.model.NewsWithNumber;
import com.example.dataservice.service.DataService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dataService")
public class DataController {
    private final DataService dataService;

    private final NewsMapper newsMapper;

    private final CommentMapper commentMapper;

    public DataController(DataService dataService, NewsMapper newsMapper, CommentMapper commentMapper) {
        this.dataService = dataService;
        this.newsMapper = newsMapper.INSTANCE;
        this.commentMapper = commentMapper.INSTANCE;
    }

    @GetMapping("/getNews")
    ResponseEntity<NewsDTO> getNewsById(@RequestParam int id) {
        try {
            News news = dataService.findNewsById(id);
            NewsDTO newsDto = newsMapper.toDTO(news);
            return ResponseEntity.ok(newsDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getComment")
    ResponseEntity<CommentDTO> getCommentById(@RequestParam int id) {
        try {
            Comment comment = dataService.findCommentById(id);
            CommentDTO commentDto = commentMapper.toDTO(comment);
            return ResponseEntity.ok(commentDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/getComments")
    ResponseEntity<List<CommentDTO>> getCommentsByNewsId(@RequestParam int id) {
        List<CommentDTO> list = dataService.findCommentsByNewsId(id)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getAllNews")
    ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> list = dataService.getAllNews()
                .stream()
                .map(newsMapper::toDTO)
                .toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/getTop2NewsPoCommentam")
    ResponseEntity<List<NewsWithNumber>> getNewsWithCommentCount() {
        List<NewsWithNumber> list = dataService.findTop4NewsWithCommentCount();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getTopWithLikeNumber")
    ResponseEntity<List<NewsWithNumber>> findTopWithLikeNumber(){
        return ResponseEntity.ok(dataService.findTopWithLikeNumber());
    }
}