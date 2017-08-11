package com.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.music.model.SongInfo;

public interface SongInfoRepository extends CrudRepository<SongInfo, Integer> {

}
