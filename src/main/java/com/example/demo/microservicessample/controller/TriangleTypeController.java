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
public class TriangleTypeController {
	@Autowired
	private MicroServicesService microServicesService;
	
	/**
	 * Get the triangle type 
	 * 
	 * @param sideDimension1
	 * @param sideDimension2
	 * @param sideDimension3
	 * @return Response entity with type of triangle
	 */
	@RequestMapping(value = "/TriangleType", method = RequestMethod.GET)
	public ResponseEntity<String> fetchTriangleType(@RequestParam("a")  int sideDimension1,@RequestParam("b")  int sideDimension2,@RequestParam("c")  int sideDimension3) {
		return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache()).header("Pragma", "no-cache")
                .body(microServicesService.fetchTriangleType(sideDimension1, sideDimension2, sideDimension3));
	}
}
