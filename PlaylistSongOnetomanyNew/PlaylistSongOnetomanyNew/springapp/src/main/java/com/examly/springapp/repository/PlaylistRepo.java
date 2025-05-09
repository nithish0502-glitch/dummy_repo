package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Playlist;


@Repository
public interface PlaylistRepo extends JpaRepository<Playlist,Integer>{
    boolean existsByName(String playlistName); 
}
