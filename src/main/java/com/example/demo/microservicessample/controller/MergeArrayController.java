package com.example.demo.microservicessample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.microservicessample.model.RestRequestModel;
import com.example.demo.microservicessample.model.RestResponseModel;
import com.example.demo.microservicessample.service.MicroServicesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "MakeOneArray")
public class MergeArrayController {

	@Autowired
	private MicroServicesService microServicesService;
	// Logger to log details
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Return merged and sorted integer array
	 * 
	 * @param IntArrayList
	 *            - list of integer array
	 * @return Response Entity
	 */
	@RequestMapping(value = "/MakeOneArray", method = RequestMethod.POST)
	@ApiOperation(value = "Merges all arrays into one", notes = "Program to combine all arrays into one by removing duplicates and maintaining order")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successs") })
	public ResponseEntity<Object> mergeToOneArray(@RequestBody RestRequestModel restRequest) {
		logger.debug("mergeToOneArray: API called /MakeOneArray");
		RestResponseModel restResponse = microServicesService.mergeToAnArray(restRequest);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache()).header("Pragma", "no-cache")
				.body(restResponse);
	}
}
