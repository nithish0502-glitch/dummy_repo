package com.examly.springapp.service;

import com.examly.springapp.model.Playlist;

public interface PlaylistService {

        public Playlist addPlayList(Playlist playlist);
        public Playlist getPlayListById(int playlistId);

}
