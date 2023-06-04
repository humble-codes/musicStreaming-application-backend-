package com.rudra.musicStreaming.services;

import com.rudra.musicStreaming.dto.PlaylistDto;
import com.rudra.musicStreaming.mappers.PlaylistMapper;
import com.rudra.musicStreaming.models.Playlist;
import com.rudra.musicStreaming.repositories.PlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    PlaylistRepo playlistRepo ;
    @Autowired
    PlaylistMapper playlistMapper ;

    @Cacheable(value = "playlistsDto" , key = "'allPlaylists'")
    public List<PlaylistDto> getAllPlaylists() {
        List <Playlist> playlists = new ArrayList<>() ;
        List <PlaylistDto> playlistsDto = new ArrayList<>() ;
        playlistRepo.findAll().forEach(playlists::add);
        playlists.forEach( playlist -> {
            playlistsDto.add(playlistMapper.playlistToDto(playlist)) ;
        });

        return playlistsDto ;
    }

    public Playlist getPlaylistByID(Long playlistID) {
        Playlist playlist = playlistRepo.findById(playlistID).orElseThrow(
                () -> new EntityNotFoundException("Playlist doesn't exists with ID " + playlistID)
        ) ;

        return playlist ;
    }

    public Playlist createPlaylist(Playlist reqPlaylist) {

        Playlist newPlaylist = new Playlist() ;
        newPlaylist.setName(reqPlaylist.getName());
        newPlaylist.setDescription(reqPlaylist.getDescription());

        // later add option for adding playlists with songs

        return playlistRepo.save(newPlaylist) ;
    }

    @CachePut(value = "playlistDto" , key = "#playlistID")
    public PlaylistDto updatePlaylist (Long playlistID , Playlist reqPlaylist) {

        Playlist oldPlaylist = playlistRepo.findById(playlistID).orElseThrow(
                () -> new EntityNotFoundException()
        ) ;

        oldPlaylist.setName(reqPlaylist.getName());
        oldPlaylist.setDescription(reqPlaylist.getDescription());

        Playlist newPlaylist = playlistRepo.save(oldPlaylist) ;
        return playlistMapper.playlistToDto(newPlaylist) ;
    }


    @Caching(
            evict = {
                    @CacheEvict(value = "playlistsDto" , key = "'allPlaylists'"),
                    @CacheEvict(value = "playlistDto" , key = "#playlistID")

            })
    public void deletePlaylist(Long playlistID) {
        playlistRepo.deleteById(playlistID);
    }

    @Cacheable(value = "playlistDto" , key = "#playlistID")
    public PlaylistDto getPlaylistDtoByID(Long playlistID) {
        Playlist playlist = playlistRepo.findById(playlistID).orElseThrow(
                () -> new EntityNotFoundException()
        );
        return playlistMapper.playlistToDto(playlist) ;
    }

    public List<PlaylistDto> getPlaylistsBySongYearAfter (Long year) {
        List<Playlist> playlists = playlistRepo.findDistinctBySongsYearAfter( ((Number)year).intValue() ) ;
        System.out.print("---> ---> ---> ---> ---> ---> ---> ---> ---> --->");
        System.out.println("Number of items returned = " + playlists.size());
        List <PlaylistDto> playlistsDto = new ArrayList<>() ;
        playlists.forEach(playlist -> {
            playlistsDto.add(playlistMapper.playlistToDto(playlist));
        });

        return playlistsDto ;
    }
}
