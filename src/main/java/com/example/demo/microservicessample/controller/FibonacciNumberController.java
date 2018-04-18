package com.example.demo.microservicessample.controller;

import java.util.HashMap;
import java.util.Map;

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
@Api(value = "FibonacciNumber")
public class FibonacciNumberController {

	@Autowired
	private MicroServicesService microServicesService;

	// Logger to log details
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Return response entity with nth Fibonacci number
	 * 
	 * @param nthFibonacciNum
	 * @return Response Entity
	 */
	@RequestMapping(value = "/Fibonacci", method = RequestMethod.GET)
	@ApiOperation(value = "Returns the nth index in the Fibonacci series", notes = "Program to calculate the nth index of the Fibonacci series")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Invalid Input: Please input a number to get a proper output") })
	public ResponseEntity<Object> getNthFibonacciNumber(@RequestParam("n") Object nthFibonacciNum) {
		try {
			logger.debug("getNthFibonacciNumber: API Called : /Fibonacci?n=" + nthFibonacciNum);
			Long nthFibNum = new Long(nthFibonacciNum.toString());
			if(nthFibNum <= 0 ) {
				Map<String, String> response = new HashMap<String, String>();
				response.put("error", "Input param value should not be less than 1");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(CacheControl.noCache())
						.header("Pragma", "no-cache").body(response);
			}
			
			return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache())
					.header("Pragma", "no-cache").body(microServicesService.getNthFibonacciNumber(nthFibNum));
		} catch (NumberFormatException numberFormatExcpetion) {
			logger.error("getNthFibonacciNumber: Invalid input :: "+numberFormatExcpetion.getMessage());
			Map<String, String> response = new HashMap<String, String>();
			response.put("error", "Invalid parameter value");
			return ResponseEntity.badRequest().body(response);
		}
	}
}
