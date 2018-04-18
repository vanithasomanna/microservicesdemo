package com.example.demo.microservicessample.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * class to hold array of objects as a request
 * @author vanitha.somanna
 *
 */
public class RestRequestModel implements Serializable{

	private static final long serialVersionUID = -8794413180188211951L;

	private Map<String, List<Integer>> values =new HashMap<String, List<Integer>>();
	
	@JsonAnyGetter
	public Map<String,List<Integer>> getValues() {
		return values;
	}
	@JsonAnySetter
	public void setValues(String name,List<Integer> value ) {
		this.values.put(name, value);
	}
}
