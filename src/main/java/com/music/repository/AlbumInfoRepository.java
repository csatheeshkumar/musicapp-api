package com.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.music.model.AlbumInfo;

public interface AlbumInfoRepository extends CrudRepository<AlbumInfo, Integer> {

}
