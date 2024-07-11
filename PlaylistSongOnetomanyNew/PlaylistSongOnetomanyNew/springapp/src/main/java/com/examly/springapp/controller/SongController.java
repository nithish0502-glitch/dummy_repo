package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Song;
import com.examly.springapp.service.SongServiceImpl;


@RestController
public class SongController {

    @Autowired
    private SongServiceImpl songserviceimpl;

    @Autowired
    private SongRepo songRepo;

    @PostMapping("song/{playlistId}/playlist")
    public ResponseEntity<Song> addSong(@PathVariable int playlistId,@RequestBody Song song)
    {
        Song newSong = songserviceimpl.addSong(playlistId,song);
        if(newSong!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(newSong);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/song")
    public List<Song> getAllSong(){

    }


@GetMapping("song/{artist}")
public ResponseEntity <List<Song>> getSongsByArtist(@PathVariable String artist)
{
    List<Song> songs = songserviceimpl.getSongByArtistName(artist);
    if(!songs.isEmpty())
    {
        return ResponseEntity.status(HttpStatus.OK).body(songs);
    }
    else
    {
        return ResponseEntity.notFound().build();
    }
}



@DeleteMapping("song/{songId}")
public String deletSongById(@PathVariable int songId)
{
    boolean deleteSong = songserviceimpl.deleteSongById(songId);
    if(deleteSong)
    {
        return "Song "+songId+" deleted successfully";
    }
    else
    {
        return "Song "+songId+" not found";
    }
}
   
    
}
