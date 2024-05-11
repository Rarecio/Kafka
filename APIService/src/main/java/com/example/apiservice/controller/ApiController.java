package com.example.apiservice.controller;

import com.example.apiservice.dto.CommentDto;
import com.example.apiservice.dto.NewsDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/apiService")
public class ApiController {

    @Value("${kafka.news.topic}")
    private String newsTopic;

    @Value("${kafka.comment.topic}")
    private String commentTopic = "comment-topic";

    @Autowired
    @Qualifier("newsKafkaTemplate")
    private KafkaTemplate<Integer, NewsDto> newsKafkaTemplate;

    @Autowired
    @Qualifier("commentKafkaTemplate")
    private KafkaTemplate<Integer, CommentDto> commentKafkaTemplate;

    @Value("${data-service.base-url}")
    private String dataServiceUrl;// = "http://localhost:8080/dataService";

    @GetMapping("/")
    ResponseEntity<String> test() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getNews")
    ResponseEntity<NewsDto> getNewsById(@RequestParam int id) {
        try {
            URL url = new URL(dataServiceUrl + "/getNews?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            NewsDto newsDto = gson.fromJson(jsonElement, NewsDto.class);
            return ResponseEntity.ok(newsDto);
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllNews")
    ResponseEntity<?> getAllNews() {
        try {
            URL url = new URL(dataServiceUrl + "/getAllNews");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(inputStream));
            System.out.println(jsonElement);
            Gson gson = new Gson();
            List<NewsDto> newsDtoList = gson.fromJson(jsonElement, List.class);
            System.out.println(newsDtoList);
            return ResponseEntity.ok(jsonElement);
//            return ResponseEntity.ok(newsDto);
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getComment")
    ResponseEntity<CommentDto> getCommentById(@RequestParam int id) {
        try {
            URL url = new URL(dataServiceUrl + "/getComment?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            CommentDto commentDto = gson.fromJson(jsonElement, CommentDto.class);
            return ResponseEntity.ok(commentDto);
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getComments")
    ResponseEntity<List<CommentDto>> getCommentsByNewsId(@RequestParam int id) {
        try {
            URL url = new URL(dataServiceUrl + "/getComments?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            List<CommentDto> list = gson.fromJson(jsonElement, List.class);
            return ResponseEntity.ok(list);
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/addNews")
    ResponseEntity<String> addNews(@RequestBody NewsDto newsDto) {
        newsKafkaTemplate.send(newsTopic, newsDto);
        return ResponseEntity.ok("added");
    }

    @PostMapping("/addComment")
    ResponseEntity<String> addComment(@RequestBody CommentDto commentDto) {
        commentKafkaTemplate.send(commentTopic, commentDto);
        return ResponseEntity.ok("added");
    }
}
