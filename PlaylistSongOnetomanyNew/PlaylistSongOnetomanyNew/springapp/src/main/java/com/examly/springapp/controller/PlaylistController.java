package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.DuplicatePlaylistException;
import com.examly.springapp.model.Playlist;
import com.examly.springapp.service.PlaylistServiceImpl;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistServiceImpl playlistserviceImpl;
    
    @PostMapping("playlist")
    public ResponseEntity<Playlist> addPlayList(@RequestBody Playlist playlist)
    {
        try {
            Playlist newplaylist = playlistserviceImpl.addPlayList(playlist);
            return ResponseEntity.status(HttpStatus.CREATED).body(newplaylist);
        } catch (DuplicatePlaylistException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions if needed
            String errorMessage = "Unexpected error occurred: " + e.getMessage();
            System.out.println(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    

    @GetMapping("/playlist/{playlistId}")
    public ResponseEntity<Playlist> getPlayListId(@PathVariable int playlistId)
    {
        Playlist playlist = playlistserviceImpl.getPlayListById(playlistId);
        if(playlist!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(playlist);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }

    }

}
