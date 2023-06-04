package com.rudra.musicStreaming.mappers;

import com.rudra.musicStreaming.dto.PlaylistDto;
import com.rudra.musicStreaming.dto.SongDto;
import com.rudra.musicStreaming.models.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SongMapper {
    SongMapper MAPPER = Mappers.getMapper(SongMapper.class) ;

    SongDto songToDto (Song entity) ;

    Song toEntity (SongDto dto) ;
}
