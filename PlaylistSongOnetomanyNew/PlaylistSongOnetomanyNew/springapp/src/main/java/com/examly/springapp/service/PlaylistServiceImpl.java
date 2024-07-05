package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicatePlaylistException;
import com.examly.springapp.model.Playlist;
import com.examly.springapp.repository.PlaylistRepo;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    
    @Autowired
    private PlaylistRepo playlistRepo;

    public Playlist addPlayList(Playlist playlist)
    {
        if (playlist.existByName(playlist.getName())) {
            throw new DuplicatePlaylistException("Playlist with name " + playlist.getName() + " already exists!");
        }
        
        // If not exists, save the department
        return playlistRepo.save(department);
    }


    public Playlist getPlayListById(int playlistId)
    {
     return playlistRepo.findById(playlistId).orElse(null);   
    }
}
