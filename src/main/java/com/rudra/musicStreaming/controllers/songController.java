package com.rudra.musicStreaming.controllers;

import com.rudra.musicStreaming.dto.SongDto;
import com.rudra.musicStreaming.models.Song;
import com.rudra.musicStreaming.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class songController {
    @Autowired
    SongService songService ;

    @GetMapping()
    public List<SongDto> getAllSongs () {
        return songService.getAllSongs() ;
    }

    @GetMapping("/{songID}")
    public SongDto getSongByID (@PathVariable Long songID){
        return songService.getSongDtoByID(songID) ;
    }

    @PostMapping
    public Song createSong (@RequestBody @Valid Song song) {
        return songService.createSong(song) ;
    }

    @PutMapping("/{songID}")
    public SongDto updateSong (@RequestBody @Valid Song song, @PathVariable Long songID) {
        return songService.updateSong(songID , song) ;
    }

    @DeleteMapping("/{songID}")
    public void deleteSOng (@PathVariable Long songID) {
        songService.deleteSong(songID);
    }
}


// simple crud
