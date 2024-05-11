package com.example.dataservice.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.dataservice.model.News}
 */
public record NewsDTO(Integer id, String text, int likeNumber) implements Serializable {
}