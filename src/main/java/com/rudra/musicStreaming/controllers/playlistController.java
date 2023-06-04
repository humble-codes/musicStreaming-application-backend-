package com.rudra.musicStreaming.controllers;

import com.rudra.musicStreaming.dto.PlaylistDto;
import com.rudra.musicStreaming.managers.PlaylistManager;
import com.rudra.musicStreaming.models.Playlist;
import com.rudra.musicStreaming.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class playlistController {

    @Autowired
    PlaylistService playlistService ;

    @Autowired
    PlaylistManager playlistManager ;

    @GetMapping
    public List<PlaylistDto> getAllPlaylists (){
        return playlistService.getAllPlaylists() ;
    }
    @GetMapping("/{playlistID}")
    public PlaylistDto getPlaylistByID (@PathVariable Long playlistID){
        return playlistService.getPlaylistDtoByID(playlistID) ;
    }

    @GetMapping("/songsYearAfter/{year}")
    public List<PlaylistDto> getPlaylistsBySongYearAfter(@PathVariable Long year) {
        return playlistService.getPlaylistsBySongYearAfter(year) ;
    }
    @PostMapping
    public Playlist createPLaylist (@RequestBody @Valid Playlist reqPlaylist){
        System.out.println(reqPlaylist);
        return playlistService.createPlaylist(reqPlaylist) ;
    }

    @PutMapping("/{playlistID}")
    public PlaylistDto updatePlaylist (@PathVariable Long playlistID , @RequestBody @Valid Playlist reqPlaylist) {
        return playlistService.updatePlaylist(playlistID , reqPlaylist) ;
    }

    @DeleteMapping("/{playlistID}")
    public void deletePlaylist (@PathVariable Long playlistID) {
        playlistService.deletePlaylist(playlistID);
    }

    @PostMapping("/{playlistID}/{songID}")
    public PlaylistDto addSongToPlaylist (@PathVariable Long songID , @PathVariable Long playlistID){
        return playlistManager.addSongToPlaylist(songID , playlistID) ;
    }

    @DeleteMapping("/{playlistID}/{songID}")
    public void removeSongFromPlaylist (@PathVariable Long playlistID , @PathVariable Long songID){
        playlistManager.removeSongFromPlaylist(songID , playlistID) ;
    }
}
