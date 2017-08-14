package com.music.service.impl;

import com.music.dto.ArtistDTO;
import com.music.model.ArtistInfo;
import com.music.repository.ArtistInfoRepository;
import com.music.service.ArtistInfoService;

import org.apache.commons.lang.StringUtils;
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
	public Integer saveArtistInfo(ArtistDTO artistDTO) {
		ArtistInfo artistInfo = null;
		if(artistDTO.getId()!=null&&StringUtils.isNotBlank(artistDTO.getId().toString())){
			artistInfo=artistInfoRepository.findOne(artistDTO.getId());
			//department.setStatus(deptDTO.getStatus());
		}else{
			artistInfo = new ArtistInfo();
			artistInfo.setStatus("Active");
			artistInfo.setProfilePicUrl(" ");
		}
		artistInfo.setArtistName(artistDTO.getArtistName());
		if(artistDTO.getFollowers()!=null){
			artistInfo.setFollowers(artistDTO.getFollowers());
		}
		artistInfo=artistInfoRepository.save(artistInfo);
		if(artistInfo!=null){
			return artistInfo.getId();
		}
		return 0;
	}

}
