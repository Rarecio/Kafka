package com.example.apiservice.dto;

import java.io.Serializable;

public record CommentDto(Integer id, String text, int likeNumber, Integer newsId) implements Serializable {
}