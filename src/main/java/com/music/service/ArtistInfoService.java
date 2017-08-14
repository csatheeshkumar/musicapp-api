package com.music.service;

import com.music.dto.ArtistDTO;

public interface ArtistInfoService {
	
	public boolean validateArtistNameAlreadyExist(ArtistDTO artistDTO);
	
	public Integer saveArtistInfo(ArtistDTO artistDTO);

}
