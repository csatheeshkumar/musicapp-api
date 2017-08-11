package com.music.api.testcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.music.dto.StatusResponseDTO;

@RestController
@RequestMapping(value = "/musicapp/api/test")
@CrossOrigin
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	
	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = { "application/json" })
	public  ResponseEntity<String> getTest(){
		LOG.info("Inside getAllNfc of NfcTagController Start");
		StatusResponseDTO statusResponseDTO =new StatusResponseDTO();
		statusResponseDTO.setMessage("Working Successfully");
		statusResponseDTO.setStatus("Success");
		return new ResponseEntity<String>(new Gson().toJson(statusResponseDTO), HttpStatus.CONFLICT);
	}

}
