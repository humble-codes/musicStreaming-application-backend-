package com.rudra.musicStreaming.dto;

import java.io.Serializable;
import java.util.Set;

public class SongDto implements Serializable {
    private Long id ;
    private String name ;
    private String artist ;
    private String album ;

    private Integer year ;

    // constructors

    public SongDto() {
    }

    public SongDto(Long id, String name, String artist, String album, Integer year) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    // getter & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
