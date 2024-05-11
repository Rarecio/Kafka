package com.example.apiservice.dto;

import java.io.Serializable;
public record NewsDto(Integer id, String text, int likeNumber) implements Serializable {
}