package com.rudra.musicStreaming.managers;

import com.rudra.musicStreaming.dto.PlaylistDto;
import com.rudra.musicStreaming.models.Playlist;
import com.rudra.musicStreaming.models.Song;
import com.rudra.musicStreaming.services.PlaylistService;
import com.rudra.musicStreaming.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistManager {
    @Autowired
    SongService songService ;
    @Autowired
    PlaylistService playlistService ;

    @CachePut(value = "playlistDto" , key = "#playlistID")
    public PlaylistDto addSongToPlaylist (Long songID , Long playlistID ) {

        // get the playlist and song, if exists
        Playlist playlist = playlistService.getPlaylistByID(playlistID) ;
        Song song = songService.getSongByID(songID) ;

        playlist.addSong(song);
        return playlistService.updatePlaylist(playlistID , playlist) ; // later try to remove another check for playlistID through this function

    }

    public void removeSongFromPlaylist (Long songID , Long playlistID) {
        // get playlist and song, if exists
        Playlist playlist = playlistService.getPlaylistByID(playlistID) ;
        Song song = songService.getSongByID(songID) ;

        playlist.removeSong(song);
        playlistService.updatePlaylist(playlistID, playlist) ;  // same improvement needed as above method
    }



}

// add song to playlist
// remove song from playlist but don't delete the orphan song

