package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Playlist;
import com.examly.springapp.model.Song;
import com.examly.springapp.repository.PlaylistRepo;
import com.examly.springapp.repository.SongRepo;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepo songrepo;

    @Autowired
    private PlaylistRepo playlistRepo;

    public Song addSong(int playlistId,Song song)
    {
        Playlist playlist = playlistRepo.findById(playlistId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid playlist Id: " + playlistId));
        song.setPlaylist(playlist);
        return songrepo.save(song);
    }

    public List<Song> getSongByArtistName(String artist)
    {
        return songrepo.findSongByArtist(artist);
    }

    public boolean deleteSongById(int songId)
    {
        if(songrepo.existsById(songId))
        {
            songrepo.deleteById(songId);
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
