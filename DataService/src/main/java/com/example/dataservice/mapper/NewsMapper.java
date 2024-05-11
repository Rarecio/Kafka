package com.example.dataservice.mapper;

import com.example.dataservice.dto.NewsDTO;
import com.example.dataservice.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper( NewsMapper.class );

    NewsDTO toDTO(News news);

    News toEntity(NewsDTO newsDTO);
}