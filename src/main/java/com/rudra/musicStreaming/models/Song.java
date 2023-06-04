package com.rudra.musicStreaming.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank(message = "song name cannot be blank")
    private String name ;
    @NotBlank(message = "artist cannot be blank")
    private String artist ;
    private String album ;
    @NotNull(message = "year cannot be blank")
    private Integer year ;
    private String genre ;
    private Integer duration ;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "songs")
    private Set<Playlist> playlists = new HashSet<>() ;

    // constructors
    public Song() {
    }

    public Song(Long id, String name, String artist, String album, Integer year, String genre, Integer duration, Set<Playlist> playlists) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.playlists = playlists;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    // getters
    public Long getId() {
        return id;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists ;
    }

}
