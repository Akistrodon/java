package com.ngm01.lookify.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ngm01.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
	ArrayList<Song> findTop10ByOrderByRatingDesc();
}
