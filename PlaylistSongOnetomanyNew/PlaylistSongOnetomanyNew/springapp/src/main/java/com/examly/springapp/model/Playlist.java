package com.examly.springapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Playlist {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private int playlistId;
   private String name;
   private String description;
   private int numofSongs;

   @OneToMany(mappedBy="playlist",cascade = CascadeType.ALL)
   @JsonManagedReference
   private List<Song> songs;

   
public Playlist() {
}
public int getPlaylistId() {
    return playlistId;
}
public void setPlaylistId(int playlistId) {
    this.playlistId = playlistId;
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

public int getNumofSongs() {
    return numofSongs;
}

public void setNumofSongs(int numofSongs) {
    this.numofSongs = numofSongs;
}

public List<Song> getSongs() {
    return songs;
}


public void setSongs(List<Song> songs) 
{
    this.songs = songs;
}

}
   

    

