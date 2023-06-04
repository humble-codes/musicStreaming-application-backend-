package com.rudra.musicStreaming.controllers;

import com.rudra.musicStreaming.dto.UserDto;
import com.rudra.musicStreaming.managers.UserManager;
import com.rudra.musicStreaming.models.Playlist;
import com.rudra.musicStreaming.models.User;
import com.rudra.musicStreaming.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    UserService userService ;
    @Autowired
    UserManager userManager ;

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getAllUsers() ;
    }

    @GetMapping("/{userID}")
    public UserDto getUser (@PathVariable Long userID ){
        return userService.getUserDtoByID(userID) ;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User user){
        return userService.createUser(user) ;
    }

    @PutMapping("/{userID}")
    public UserDto updateUser(@RequestBody @Valid User reqUser , @PathVariable Long userID) {
        return userService.updateUser(reqUser , userID) ;
    }

    @DeleteMapping("/{userID}")
    public void removeUser (@PathVariable Long userID) {
        userService.removeUser(userID) ;
    }

    @PostMapping("/{userID}/playlists")
    public UserDto addPlaylist (@PathVariable Long userID , @RequestBody @Valid Playlist reqPlaylist){
        System.out.println("we have hit this api");
        return userManager.addPlaylist (userID , reqPlaylist) ;
    }

    @DeleteMapping("/{userID}/playlists/{playlistID}")
    public void addPlaylist (@PathVariable Long userID , @PathVariable Long playlistID){
        userManager.removePlaylist (userID , playlistID); ;
    }
}
