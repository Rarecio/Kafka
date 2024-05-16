package com.example.dataservice.mapper;

import com.example.dataservice.dto.NewsWithNumberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = NewsMapper.class)
public interface NewsWithNumberMapper {

    NewsWithNumberMapper INSTANCE = Mappers.getMapper(NewsWithNumberMapper.class);

//    @Mapping(target = "newsDTO", source = "news")
    NewsWithNumberDTO toDTO(NewsWithNumberDTO newsWithNumberDTO);

//    @Mapping(target = "news", source = "newsDTO")
    NewsWithNumberDTO toEntity(NewsWithNumberDTO newsWIthNumberDTO);
}
