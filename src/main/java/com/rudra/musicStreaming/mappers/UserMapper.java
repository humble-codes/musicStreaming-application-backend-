package com.rudra.musicStreaming.mappers;

import com.rudra.musicStreaming.dto.PlaylistDto;
import com.rudra.musicStreaming.dto.SongDto;
import com.rudra.musicStreaming.dto.UserDto;

import com.rudra.musicStreaming.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring" , uses = {PlaylistMapper.class , SongMapper.class})
@Component
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "playlists", target = "playlistsDto")
    UserDto usertoUserDto(User user) ;

    @Mapping(source = "playlistsDto", target = "playlists")
    User userDtoToCustomer(UserDto userDto) ;
}
