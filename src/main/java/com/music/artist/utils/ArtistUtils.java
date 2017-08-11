package com.music.artist.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.music.dto.ArtistDTO;

@Service
public class ArtistUtils {
	
	
	public boolean validateArtistParam(ArtistDTO artistDTO) {
		if ((artistDTO.getArtistName()!= null && StringUtils.isNotBlank(artistDTO.getArtistName()))
				&& (artistDTO.getProfilePicUrl()!= null && StringUtils.isNotBlank(artistDTO.getProfilePicUrl()))
				&& (artistDTO.getFollowers()!= null && StringUtils.isNotBlank(artistDTO.getFollowers().toString()))) {
			return true;
		}
		return false;
	}
	

}
