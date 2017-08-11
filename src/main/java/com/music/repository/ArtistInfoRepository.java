package com.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.music.model.ArtistInfo;

public interface ArtistInfoRepository extends CrudRepository<ArtistInfo, Integer> {
	
	Integer countArtistInfoByArtistNameIgnoreCase(String artistName);

}
