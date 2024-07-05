package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Playlist;
import com.examly.springapp.repository.PlaylistRepo;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    
    @Autowired
    private PlaylistRepo playlistRepo;

    public Playlist addPlayList(Playlist playlist)
    {
        try
        {
            playlistRepo.save(playlist);
            return playlist;
        }catch(Exception e)
        {
         e.printStackTrace();
         return null;   
        }
        
    }


    public Playlist getPlayListById(int playlistId)
    {
     return playlistRepo.findById(playlistId).orElse(null);   
    }
}
