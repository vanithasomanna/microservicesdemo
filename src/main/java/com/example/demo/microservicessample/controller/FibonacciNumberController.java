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
public class FibonacciNumberController {
	
	@Autowired
	private MicroServicesService microServicesService;
	
	/**
	 * Return response entity with nth Fibonacci number
	 * 
	 * @param nthFibonacciNum
	 * @return Response Entity
	 */
	@RequestMapping(value = "/Fibonacci", method = RequestMethod.GET)
	public ResponseEntity<Integer> getNthFibonacciNumber(@RequestParam("n")  long nthFibonacciNum) {
		return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache()).header("Pragma", "no-cache")
                .body(microServicesService.getNthFibonacciNumber(nthFibonacciNum));

	}
}
