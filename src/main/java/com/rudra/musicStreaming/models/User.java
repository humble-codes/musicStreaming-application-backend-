package com.rudra.musicStreaming.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank(message = "username cannot be blank")
    private String username ;
    @NotEmpty(message = "password cannot be empty")
    private String password ;
    @Email(message = "invalid email address")
    private String email ;
    private String firstname ;
    private String lastname ;

    @OneToMany(
            mappedBy = "user" ,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST , CascadeType.MERGE} ,
            orphanRemoval = true
    )
    private List<Playlist> playlists = new ArrayList<>() ;

    // getters and setters

    // constructors
    public User() {
    }

    public User(Long id, String username, String password, String email, String firstname, String lastname, List<Playlist> playlists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.playlists = playlists;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    // only getters
    public Long getId() {
        return id;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    // some additional methods
    public void addPlaylist (Playlist playlist) {
//        this.getPlaylists().add(playlist) ;
//        playlist.setUser(this);
        this.playlists.add(playlist) ;
        playlist.setUser(this);
    }

    public void removePlaylist (Playlist playlist){
//        this.getPlaylists().remove(playlist) ;
//        playlist.setUser(this);
        this.playlists.remove(playlist) ;
        playlist.setUser(null);
    }
}
