package com.music.api.artistcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.music.artist.utils.ArtistUtils;
import com.music.dto.StatusResponseDTO;
import com.music.service.ArtistInfoService;
import com.music.dto.ArtistDTO;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/musicapp/api/artist")
@CrossOrigin
public class ArtistController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ArtistController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	ArtistUtils artistUtils;
	
	@Autowired
	ArtistInfoService artistInfoService;
	
	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.GET, produces = { "application/json" })
	public  ResponseEntity<String> addArtist(@ApiParam(value = "Required Nfc Details", required = true) @RequestBody ArtistDTO artistDTO){
		LOG.info("Inside addArtist of ArtistController Start");
		StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
		statusResponseDTO.setStatus(env.getProperty("failure"));
		try{
			if (!artistUtils.validateArtistParam(artistDTO)) {
				statusResponseDTO.setMessage(env.getProperty("incorrectDetails"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.CONFLICT);
			}
			
			if (artistInfoService.validateArtistNameAlreadyExist(artistDTO)) {
				statusResponseDTO.setMessage(env.getProperty("artist.name.exist"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}
			boolean isNfcSaved = artistInfoService.saveArtistInfo(artistDTO);
			if (!isNfcSaved) {
				statusResponseDTO.setMessage(env.getProperty("artist.create.failure"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}					
			
			statusResponseDTO.setStatus(env.getProperty("success"));
			statusResponseDTO.setMessage(env.getProperty("artist.create.success"));
			return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Problem in createNewNfc  : ", e);
			statusResponseDTO.setMessage(env.getProperty("server.problem"));
			return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.CONFLICT);
		}		
	}

}
