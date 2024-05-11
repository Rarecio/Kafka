package com.example.dataservice.mapper;

import com.example.dataservice.dto.CommentDTO;
import com.example.dataservice.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = NewsMapper.class)
//@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "newsId", source = "news.id")
    CommentDTO toDTO(Comment comment);

    @Mapping(target = "news.id", source = "newsId")
    Comment toEntity(CommentDTO commentDTO);

//    default News newsDTOToNews(NewsDTO newsDTO) {
//        return Mappers.getMapper(NewsMapper.class).toEntity(newsDTO);
//    }
//
//    default NewsDTO newsToNewsDTO(News news) {
//        return Mappers.getMapper(NewsMapper.class).toDTO(news);
//    }

}
