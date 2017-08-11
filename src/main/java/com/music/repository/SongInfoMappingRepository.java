package com.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.music.model.SongInfoMapping;

public interface SongInfoMappingRepository extends CrudRepository<SongInfoMapping, Integer> {

}
