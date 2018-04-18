package com.example.demo.microservicessample.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class to hold response
 * @author vanitha.somanna
 *
 */
public class RestResponseModel implements Serializable{

	private static final long serialVersionUID = 7625748343752321519L;
	
	public RestResponseModel() {

	}
	public RestResponseModel(Object[] array) {

		this.array=array;

	}
	
	@JsonProperty("Array")
	private Object[] array;

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}
	
}
