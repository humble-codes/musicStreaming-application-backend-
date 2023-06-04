package com.rudra.musicStreaming.dto;


import java.io.Serializable;
import java.util.List;


public class UserDto implements Serializable {
    private Long id ;
    private String username ;
    private String email ;
    private String firstname ;
    private String lastname ;

    private List<PlaylistDto> playlistsDto ;

    // constructors
    public UserDto() {
    }

    public UserDto(Long id, String username, String email, String firstname, String lastname, List<PlaylistDto> playlistsDto) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.playlistsDto = playlistsDto;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<PlaylistDto> getPlaylistsDto() {
        return playlistsDto;
    }

    public void setPlaylistsDto(List<PlaylistDto> playlistsDto) {
        this.playlistsDto = playlistsDto;
    }

    // toString method


}
