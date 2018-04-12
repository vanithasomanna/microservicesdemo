package com.example.demo.microservicessample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.microservicessample.model.IntArrayList;
import com.example.demo.microservicessample.service.MicroServicesService;

@RestController
@RequestMapping("/api")
public class MergeArrayController {

	@Autowired
	private MicroServicesService microServicesService;

	/**
	 * Return merged and sorted integer array
	 * 
	 * @param IntArrayList - list of integer array
	 * @return Response Entity
	 */
	@RequestMapping(value = "/MakeOneArray", method = RequestMethod.POST)
	public ResponseEntity<Object> mergeToOneArray(@RequestBody IntArrayList integerArray) {
		IntArrayList arrayList = microServicesService.mergeToAnArray(integerArray);
		Map<String, List<Integer>> response = new HashMap<String, List<Integer>>();
		response.put("Array", arrayList.getArrays());
		return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache()).header("Pragma", "no-cache")
				.body(response);
	}

}
