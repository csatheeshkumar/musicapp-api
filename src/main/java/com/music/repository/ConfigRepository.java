package com.music.repository;

import org.springframework.data.repository.CrudRepository;

import com.music.model.Config;

public interface ConfigRepository extends CrudRepository<Config, Integer> {
	public Config findConfigByKey(String key);
	

}
