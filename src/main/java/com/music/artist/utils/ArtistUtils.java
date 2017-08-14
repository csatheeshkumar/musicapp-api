package com.music.artist.utils;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.music.dto.ArtistDTO;

@Service
public class ArtistUtils {
	
	public static final Logger LOG = LoggerFactory.getLogger(ArtistUtils.class);
	
	public boolean validateArtistParam(ArtistDTO artistDTO) {
		if ((artistDTO.getArtistName()!= null && StringUtils.isNotBlank(artistDTO.getArtistName()))
				&& (artistDTO.getFollowers()!= null && StringUtils.isNotBlank(artistDTO.getFollowers().toString()))) {
			return true;
		}
		return false;
	}
	
	public boolean isImage(MultipartFile file) {
		try {
			BufferedImage image = ImageIO.read(file.getInputStream());
			if (image != null) {
				return true;
			}
			LOG.info("Invalid image type");
			return false;
		} catch (Exception e) {
			LOG.info("Invalid image type");
			return false;
		}
	}
	

}
