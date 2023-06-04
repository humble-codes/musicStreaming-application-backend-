package com.rudra.musicStreaming.repositories;

import com.rudra.musicStreaming.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends CrudRepository<Song, Long> {
}
