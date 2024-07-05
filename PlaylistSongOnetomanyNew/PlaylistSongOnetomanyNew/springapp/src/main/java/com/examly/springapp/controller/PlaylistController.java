package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Playlist;
import com.examly.springapp.service.PlaylistServiceImpl;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistServiceImpl playlistserviceImpl;
    
    @PostMapping("playlist")
    public ResponseEntity<Playlist> addPlayList(@RequestBody Playlist playlist)
    {
        Playlist newplaylist = playlistserviceImpl.addPlayList(playlist);
        if(newplaylist!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(newplaylist);
        }
    
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
