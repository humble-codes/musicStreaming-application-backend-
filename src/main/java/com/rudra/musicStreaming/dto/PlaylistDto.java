package com.rudra.musicStreaming.dto;

import java.io.Serializable;
import java.util.Set;

public class PlaylistDto implements Serializable {
    private Long id ;
    private String name ;
    private String description ;
    private Set<SongDto> songsDto;

    // constructors

    public PlaylistDto() {
    }

    public PlaylistDto(Long id, String name, String description, Set<SongDto> songsDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.songsDto = songsDto;
    }

    // getters & setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SongDto> getSongsDto() {
        return songsDto;
    }

    public void setSongsDto(Set<SongDto> songsDto) {
        this.songsDto = songsDto;
    }
}
