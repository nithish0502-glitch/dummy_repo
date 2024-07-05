package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Song;

public interface SongService {
        public Song addSong(int playlistId,Song song);
        public List<Song> getSongByArtistName(String artist);
        public boolean deleteSongById(int songId);
}
