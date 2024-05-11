package com.example.dataservice.listener;

import com.example.dataservice.dto.CommentDTO;
import com.example.dataservice.dto.NewsDTO;
import com.example.dataservice.mapper.CommentMapper;
import com.example.dataservice.mapper.NewsMapper;
import com.example.dataservice.model.Comment;
import com.example.dataservice.model.News;
import com.example.dataservice.service.DataService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

    private final DataService dataService;

    public TopicListener(DataService dataService) {
        this.dataService = dataService;
    }

    @KafkaListener(topics = "news-topic", containerFactory = "kafkaListenerContainerFactoryNews")
    void consumeNews(ConsumerRecord<Integer, NewsDTO> record){
        NewsDTO newsDto = record.value();
        News news = NewsMapper.INSTANCE.toEntity(newsDto);
        dataService.saveNews(news);
    }

    @KafkaListener(topics = "comment-topic" ,containerFactory = "kafkaListenerContainerFactoryComment")
    void consumeComment(ConsumerRecord<Integer, CommentDTO> record){
        CommentDTO commentDto = record.value();
        Comment comment = CommentMapper.INSTANCE.toEntity(commentDto);
        dataService.saveComment(comment);
    }
}
