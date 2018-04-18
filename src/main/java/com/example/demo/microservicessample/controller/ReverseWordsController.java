package com.example.demo.microservicessample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.microservicessample.service.MicroServicesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "ReverseWords")
public class ReverseWordsController {
	@Autowired
	private MicroServicesService microServicesService;
	// Logger to log details
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Reverse the given sentence
	 * 
	 * @param sentence
	 * @return Response entity with reversed words in sentences
	 */
	@ApiOperation(value = "Reverses the words of a sentence", notes = "Program to reverse words in a sentence")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid Input: Please provide a proper input") })
	@RequestMapping(value = "/ReverseWords", method = RequestMethod.GET)
	public ResponseEntity<Object> reverseWords(@RequestParam("sentence") String sentence) {
		logger.debug("reverseWords: API called: /ReverseWords");
		return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache()).header("Pragma", "no-cache")
				.body(microServicesService.reverseWords(sentence));
	}

}
