package com.rudra.musicStreaming.repositories;

import com.rudra.musicStreaming.models.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepo extends CrudRepository<Playlist , Long> {

    List<Playlist> findDistinctBySongsYearAfter(Integer year) ;
}
