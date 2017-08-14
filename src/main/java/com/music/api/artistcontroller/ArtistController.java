package com.music.api.artistcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.music.artist.utils.ArtistUtils;
import com.music.dto.StatusResponseDTO;
import com.music.fileupload.FileUploadService;
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
	
	@Autowired
	FileUploadService fileUploadService;
	
	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json" })
	public  ResponseEntity<String> addArtist(@ApiParam(value = "Required departmentInfo", required = true) @RequestParam(name = "artistInfo", value = "artistInfo", required = true) String artiststr,
		    @ApiParam(value = "Required Banner image attachment", required = true) @RequestParam(name = "file", value = "file", required = true) MultipartFile file){
		LOG.info("Inside addArtist of ArtistController Start");
		StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
		statusResponseDTO.setStatus(env.getProperty("failure"));
		try{
			ArtistDTO artistDTO=null;
			ObjectMapper mapper = new ObjectMapper();
			artistDTO = mapper.readValue(artiststr, ArtistDTO.class);
			if (!artistUtils.validateArtistParam(artistDTO)) {
				statusResponseDTO.setMessage(env.getProperty("incorrectDetails"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.CONFLICT);
			}
			if (!artistUtils.isImage(file)) {
				statusResponseDTO.setMessage(env.getProperty("image.type.invalid"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}
			
			if (artistInfoService.validateArtistNameAlreadyExist(artistDTO)) {
				statusResponseDTO.setMessage(env.getProperty("artist.name.exist"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}
			Integer isArtistSaved = artistInfoService.saveArtistInfo(artistDTO);
			if (isArtistSaved==0) {
				statusResponseDTO.setMessage(env.getProperty("artist.create.failure"));
				return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.PARTIAL_CONTENT);
			}	
			String departmentImagePath = fileUploadService.artistImageUpload(file,isArtistSaved);
			if (departmentImagePath.equalsIgnoreCase("failure")) {
				statusResponseDTO.setMessage(env.getProperty("image.upload.failure"));
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
