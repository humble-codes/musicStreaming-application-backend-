package com.rudra.musicStreaming.services;

import com.rudra.musicStreaming.dto.UserDto;
import com.rudra.musicStreaming.mappers.UserMapper;
import com.rudra.musicStreaming.models.User;
import com.rudra.musicStreaming.repositories.UserRepo;
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
public class UserService {

    @Autowired
    UserRepo userRepo ;
    @Autowired
    UserMapper userMapper ;


    public User getUserByID(Long userID) {
        User user = userRepo.findById(userID).orElseThrow (
                () -> new EntityNotFoundException("User not found with id " + userID)
        ) ;

        return user ;
    }
    @Cacheable(value = "usersDto" , key = "'allUsers'")
    public List<UserDto> getAllUsers() {
        List <User> users = new ArrayList<>() ;
        userRepo.findAll().forEach(users::add);

//        List <UserDto> usersDto = users.stream().map(user ->
//            userMapper.usertoUserDto(user)
//        ).collect(Collectors.toList()) ;

        List <UserDto> usersDto = new ArrayList<>() ;
        users.forEach( user -> {
            usersDto.add(userMapper.usertoUserDto(user) ) ;
        });

        return usersDto ;
    }
    public User createUser(User reqUser) {
        User newUser = new User() ;
        newUser.setUsername(reqUser.getUsername());
        newUser.setEmail(reqUser.getEmail());
        newUser.setPassword(reqUser.getPassword());
        newUser.setFirstname(reqUser.getFirstname());
        newUser.setLastname(reqUser.getLastname());

        // adding playlists
//        List<Playlist> playlists = new ArrayList<>() ;
//        reqUser.getPlaylists().forEach( playlist -> {
//            playlistRepo.save(playlist) ;
//            newUser.addPlaylist(playlist);
//        });
//        newUser.setPlaylists(playlists);

        // adding playlists
        reqUser.getPlaylists().forEach( playlist -> {
//            newUser.getPlaylists().add(playlist) ;
            newUser.addPlaylist(playlist);
        });

        userRepo.save(newUser) ;

        return newUser ;
    }
    @CachePut(value="userDto", key="#userID")
    public UserDto updateUser(User reqUser, Long userID) {

        // check if user exists or not
        User oldUser = userRepo.findById(userID).orElseThrow(
                () -> new EntityNotFoundException("User doesn't exists with is " + userID )
        ) ;
        oldUser.setUsername(reqUser.getUsername());
        oldUser.setEmail(reqUser.getEmail());
        oldUser.setPassword(reqUser.getPassword());
        oldUser.setFirstname(reqUser.getFirstname());
        oldUser.setLastname(reqUser.getLastname());

        User newUser = userRepo.save(oldUser) ;
        return userMapper.usertoUserDto(newUser) ;
    }


    @Caching(
            evict = {
                    @CacheEvict(value = "usersDto" , key = "'allUsers'"),
                    @CacheEvict(value = "userDto" , key = "#userID")

            })
    public void removeUser(Long userID) {

        // check if user exists
//        User user = userRepo.findById(userID).orElseThrow(
//                () -> new EntityNotFoundException("User doesn't exists with id " + userID)
//        ) ;
//        User newUser = new User() ;
//        newUser.setUsername(user.getUsername());
//        newUser.setEmail(user.getEmail());
        userRepo.deleteById(userID) ;

    }

    @Cacheable(value = "userDto" , key = "#userID")
    public UserDto getUserDtoByID(Long userID) {
        User user = userRepo.findById(userID).orElseThrow(
                () -> new EntityNotFoundException("User doesn't exists with is " + userID)
        ) ;
        return userMapper.usertoUserDto(user) ;
    }

//    public User addPlaylist(Long userID, Playlist reqPlaylist) {
//        // check if valid user
//        User user = userRepo.findById(userID).orElseThrow(
//                () -> new EntityNotFoundException("User doesn't exists with id " + userID)
//        ) ;
//
//        Playlist playlist = playlistRepo.findById(reqPlaylist.getId()).orElse(null) ;
//
//        if ( playlist == null ){
//            playlistRepo.save(reqPlaylist) ;
//            user.addPlaylist(reqPlaylist);
//        }
//
//        else {
//            user.addPlaylist(playlist);
//        }
//        return userRepo.save(user) ;
////        return user ;
//    }
}
