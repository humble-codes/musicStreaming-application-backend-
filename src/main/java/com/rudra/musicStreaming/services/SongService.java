package com.rudra.musicStreaming.services;

import com.rudra.musicStreaming.dto.SongDto;
import com.rudra.musicStreaming.mappers.SongMapper;
import com.rudra.musicStreaming.models.Song;
import com.rudra.musicStreaming.repositories.SongRepo;
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
public class SongService {
    @Autowired
    SongRepo songRepo ;

    @Autowired
    SongMapper songMapper ;

    @Cacheable(value = "songsDto" , key = "'allSongs'")
    public List<SongDto> getAllSongs() {
        List<Song> songs = new ArrayList<>() ;
        List<SongDto> songsDto = new ArrayList<>() ;
        songRepo.findAll().forEach(songs::add);
        songs.forEach(song -> {
            songsDto.add(songMapper.songToDto(song));
        });
        return songsDto ;
    }

    public Song getSongByID(Long songID) {

        return songRepo.findById(songID).orElseThrow(
                () -> new EntityNotFoundException("Song doesn't exists with id " + songID)
        );
    }

    public Song createSong(Song reqsong ){
        Song newSong = new Song() ;
        newSong.setName(reqsong.getName());
        newSong.setAlbum(reqsong.getAlbum());
        newSong.setArtist(reqsong.getArtist());
        newSong.setYear(reqsong.getYear());
        newSong.setGenre(reqsong.getGenre());
        newSong.setDuration(reqsong.getDuration());

        return songRepo.save(newSong) ;
    }

    @CachePut(value = "songDto" , key = "#songID")
    public SongDto updateSong(Long songID , Song reqSong ){

        // check if Song exists
        Song oldSong = songRepo.findById(songID).orElseThrow(
                () -> new EntityNotFoundException()
        ) ;

        oldSong.setName(reqSong.getName());
        oldSong.setArtist(reqSong.getArtist());
        oldSong.setAlbum(reqSong.getAlbum());
        oldSong.setGenre(reqSong.getGenre());
        oldSong.setYear(reqSong.getYear());
        oldSong.setDuration(reqSong.getDuration());


        Song newSong = songRepo.save(oldSong) ;
        return songMapper.songToDto(newSong) ;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "songsDto" , key = "'allSongs'"),
                    @CacheEvict(value = "songDto" , key = "#songID")

            })
    public void deleteSong (Long songID){
        songRepo.deleteById(songID);
    }

    @Cacheable(value = "songDto" , key = "#songID")
    public SongDto getSongDtoByID(Long songID) {
        Song song = songRepo.findById(songID).orElseThrow(
                () -> new EntityNotFoundException("Song doesn't exists with id " + songID)
        );

        return songMapper.songToDto(song) ;
    }
}
