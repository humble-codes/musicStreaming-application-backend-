package com.rudra.musicStreaming.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlist")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank(message = "playlist name cannot be blank")
    private String name ;
    private String description ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_song" ,
            joinColumns = {@JoinColumn(name = "playlist_id")} ,
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )
    private Set<Song> songs = new HashSet<>() ;

    // constructors
    public Playlist() {
    }

    public Playlist(Long id, String name, String description, User user, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.songs = songs;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // only getters


    public Long getId() {
        return id;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
        this.songs.forEach( song -> song.getPlaylists().add(this));
    }

    // some helper methods for managing relations
    public void addSong (Song song) {
        this.getSongs().add(song) ;
        song.getPlaylists().add(this) ;
    }

    public void removeSong (Song song) {
        this.getSongs().remove(song) ;
        song.getPlaylists().remove(this) ;
    }

}
