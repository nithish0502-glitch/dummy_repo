package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Song;

@Repository
public interface SongRepo extends JpaRepository <Song,Integer> {
    
    @Query("SELECT s FROM Song s WHERE s.artist = ?1")
    List<Song> findSongByArtist(String artist);

}
