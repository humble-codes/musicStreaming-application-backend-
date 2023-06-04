package com.rudra.musicStreaming.managers;

import com.rudra.musicStreaming.dto.UserDto;
import com.rudra.musicStreaming.models.Playlist;
import com.rudra.musicStreaming.models.User;
import com.rudra.musicStreaming.services.PlaylistService;
import com.rudra.musicStreaming.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserManager {

    @Autowired
    UserService userService ;
    @Autowired
    PlaylistService playlistService ;

    @CachePut(value = "userDto" , key = "#userID")
    public UserDto addPlaylist(Long userID, Playlist reqPlaylist) {

        // check if valid user
        User user = userService.getUserByID(userID) ;

        Playlist newPlaylist = playlistService.createPlaylist(reqPlaylist) ;
        user.addPlaylist(newPlaylist);

        return userService.updateUser(user , user.getId()) ;

    }

    public void removePlaylist (Long userID, Long playlistID){

        // check if valid User and Playlist
        User user = userService.getUserByID(userID) ;
        Playlist playlist = playlistService.getPlaylistByID(playlistID) ;

        user.removePlaylist(playlist);
        userService.updateUser(user , user.getId()) ;

        // also delete this playlist form PLAYLIST table
        // only needed if OrphanRemoval is false
//        playlistService.deletePlaylist(playlistID);

    }


}


// CRUD operations for user
// add playlist to a user
// remove a playlist from a user and thus delete the playlist
// update a playlist of a user
