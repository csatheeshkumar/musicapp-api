package com.music.service.impl;

import com.music.dto.ArtistDTO;
import com.music.repository.ArtistInfoRepository;
import com.music.service.ArtistInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistInfoServiceImpl implements ArtistInfoService{
	
	@Autowired
	ArtistInfoRepository artistInfoRepository;

	@Override
	public boolean validateArtistNameAlreadyExist(ArtistDTO artistDTO) {
		Integer countArtist=artistInfoRepository.countArtistInfoByArtistNameIgnoreCase(artistDTO.getArtistName());
		if(countArtist>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean saveArtistInfo(ArtistDTO artistDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
