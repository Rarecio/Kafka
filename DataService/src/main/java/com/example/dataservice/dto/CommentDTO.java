package com.example.dataservice.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.dataservice.model.Comment}
 */
public record CommentDTO(Integer id, String text, int likeNumber, Integer newsId) implements Serializable {
}