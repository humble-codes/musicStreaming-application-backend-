package com.rudra.musicStreaming.mappers;

import com.rudra.musicStreaming.dto.PlaylistDto;
import com.rudra.musicStreaming.dto.SongDto;
import com.rudra.musicStreaming.models.Playlist;
import com.rudra.musicStreaming.models.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring" , uses = {SongMapper.class})
@Component
public interface PlaylistMapper {

    PlaylistMapper MAPPER = Mappers.getMapper(PlaylistMapper.class) ;
    @Mapping(source = "songs" , target = "songsDto")
    PlaylistDto playlistToDto (Playlist entity) ;

    @Mapping(source = "songsDto" , target = "songs")
    Playlist toEntity (PlaylistDto dto) ;
}
