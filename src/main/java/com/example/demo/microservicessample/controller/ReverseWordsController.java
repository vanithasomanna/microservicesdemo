package com.example.demo.microservicessample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.microservicessample.service.MicroServicesService;

@RestController
@RequestMapping("/api")
public class ReverseWordsController {
	@Autowired
	private MicroServicesService microServicesService;
	
	/**
	 * Reverse the given sentence
	 * @param sentence
	 * @return Response entity with reversed words in sentences
	 */
	@RequestMapping(value = "/ReverseWords", method = RequestMethod.GET)
	public ResponseEntity<String> reverseWords(@RequestParam("sentence")  String sentence) {
		return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache()).header("Pragma", "no-cache")
                .body(microServicesService.reverseWords(sentence));
	}

}
