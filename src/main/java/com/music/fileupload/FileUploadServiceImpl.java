package com.music.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.music.model.ArtistInfo;
import com.music.model.Config;
import com.music.repository.ArtistInfoRepository;
import com.music.repository.ConfigRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileUploadServiceImpl.class);
	
	@Autowired
	ConfigRepository configRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	ArtistInfoRepository artistInfoRepository;
	
	
	
	@Override
	public boolean fileWriteIntoLocation(MultipartFile file, String oldPath, String newPath, String fileName) {
		// Check file Already Exist!
		if (oldPath != null) {
			oldPath = oldPath.replace("/", "\\");
			File oldFile = new File(oldPath);
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}
		
		FileInputStream reader = null;
		FileOutputStream writer = null;
		
		try {
			// Create Folder Location
			newPath = newPath.replace("/", "\\");
			File createfolder = new File(newPath);
			if (!createfolder.exists()) {
				createfolder.mkdirs();
			}
			
			// Create File in Folder Location
			reader = (FileInputStream) file.getInputStream();
			byte[] buffer = new byte[1000];
			File outputFile = new File(newPath + fileName);
			outputFile.createNewFile();
			writer = new FileOutputStream(outputFile);
			while ((reader.read(buffer)) != -1) {
				writer.write(buffer);
			}
			LOG.info("Created file in the Path=" + newPath + fileName);
			return true;
		} catch (Exception e) {
			LOG.info("Failed to create file in the  Path= " + newPath + fileName);
			return false;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				LOG.info("Failed to create file in the Path= " + newPath + fileName);
				return false;
			}
		}
	}

	@Override
	public String artistImageUpload(MultipartFile uploadFile, Integer id) {
		String artistImagePath = "failure";
		ArtistInfo artistInfo = artistInfoRepository.findOne(id);
		if (artistInfo == null) {
			return artistImagePath;
		}
		Config configInfo = configRepository.findConfigByKey("musicappfileslocation");
		String artistImageLocation = env.getProperty("artist.image.location");
		String imageType = Files.getFileExtension(uploadFile.getOriginalFilename());
		String fileName = artistInfo.getId().toString() + "." + imageType;
		String newPath = configInfo.getValue() + artistImageLocation;
		String oldPath = null;
		if (artistInfo.getProfilePicUrl() != null && !artistInfo.getProfilePicUrl().equalsIgnoreCase(" ")) {
			oldPath = configInfo.getValue() + artistInfo.getProfilePicUrl();
		}
		boolean isUploaded = fileWriteIntoLocation(uploadFile, oldPath, newPath, fileName);
		if (isUploaded) {
			artistInfo.setProfilePicUrl(artistImageLocation + fileName);
			artistInfo = artistInfoRepository.save(artistInfo);
			if (artistInfo != null) {
				artistImagePath=artistInfo.getProfilePicUrl();
				return artistImagePath;
			}
		}
		LOG.info("Failed to set Artist image Path");
		return artistImagePath;
	}

}
