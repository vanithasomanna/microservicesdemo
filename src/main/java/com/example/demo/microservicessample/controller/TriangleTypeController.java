package com.example.demo.microservicessample.controller;

import java.util.HashMap;
import java.util.Map;

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
	public ResponseEntity<Object> fetchTriangleType(@RequestParam("a") Object a, @RequestParam("b") Object b,
			@RequestParam("c") Object c) {
		try {
			Integer sideDimension1 = new Integer(a.toString());
			Integer sideDimension2 = new Integer(b.toString());
			Integer sideDimension3 = new Integer(c.toString());
			return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache())
					.header("Pragma", "no-cache")
					.body(microServicesService.fetchTriangleType(sideDimension1, sideDimension2, sideDimension3));
		} catch (NumberFormatException numberFormatExcpetion) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("error", "Invalid parameter value");
			return ResponseEntity.badRequest().body(response);
		}

	}
}
