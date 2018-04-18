package com.example.demo.microservicessample.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.microservicessample.model.RestRequestModel;
import com.example.demo.microservicessample.model.RestResponseModel;

@Service
public class MicroServicesService {

	// Logger to log details
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Fibonacci series starts with 0,1 To find the nth Fibonacci number
	 * 
	 * @param nthFibonacciNum
	 * @return nth fibonacci number
	 */
	public int getNthFibonacciNumber(long nthFibonacciNum) {

		logger.debug("getNthFibonacciNumber: Fetching "+ nthFibonacciNum + "st/nd/th fibonacci number");
		// Input : 0,1,2,3,5
		// variables to hold starting series of Fibonacci number
		int num1 = 1;
		int num2 = 0;
		// loop to find the nth fibonacci number
		for (int i = 0; i <= nthFibonacciNum; i++) {
			int fibonacci = num1 + num2;
			num1 = num2;
			num2 = fibonacci;
		}
		logger.debug("getNthFibonacciNumber: Returned Fibonacci number : " + num1);
		return num1;
	}

	/**
	 * To reverse the given string in the sentence
	 * 
	 * @param sentence
	 * @return reversed string
	 */
	public String reverseWords(String sentence) {
		logger.debug("reverseWords: Reverse the given string: " + sentence);
		// Input and output - how are you - woh era uoy
		// Word array to split the input sentence
		String[] wordArray = sentence.split(" ");
		// String builder to hold the reversed string builder
		StringBuilder reversedStrBuilder = new StringBuilder();
		// Loop the sentence entered word by word
		for (String word : wordArray) {
			StringBuilder reversedWord = new StringBuilder();
			// save trailing puncintation
		      String punctutations = "!@#$%^&*()_+?.";
		      String last_letter = "";
		      if( punctutations.contains(word.substring(word.length()-1))){
		         last_letter = word.substring(word.length()-1);
		         word = word.replace(word.substring(word.length()-1), "");
		      }

			
			for (int i = word.length() - 1; i >= 0; i--) {
				reversedWord.append(word.charAt(i));
			}
			reversedStrBuilder.append(reversedWord).append(last_letter).append(" ");
		}
		logger.debug("reverseWords: Reversed string: " + reversedStrBuilder);
		return reversedStrBuilder.toString();
	}

	/**
	 * To return Three types of triangle - Equilateral, Isosceles or Scalene
	 * 
	 * @param sideDimension1
	 * @param sideDimension2
	 * @param sideDimension3
	 * @return type of triangle as a String value
	 */
	public String fetchTriangleType(int sideDimension1, int sideDimension2, int sideDimension3) {
		logger.debug("fetchTriangleType: Find the trinangle type given sideDimenstion 1:" + sideDimension1
				+ " sideDimenstion 2:" + sideDimension2 + " sideDimenstion 3:" + sideDimension3);
		/*
		 * check for Equilateral triangle whose all sides are equal, else check for
		 * Isosceles triangle which has two equal sides else it will return Scalene
		 * where all three sides are not equal
		 */
		if (sideDimension1 == sideDimension2 && sideDimension2 == sideDimension3) {
			logger.debug("fetchTriangleType: Returned Triangle type : Equilateral");
			return "Equilateral";
		} else if (sideDimension1 == sideDimension2 || sideDimension2 == sideDimension3
				|| sideDimension1 == sideDimension3) {
			logger.debug("fetchTriangleType: Returned Triangle type : Isosceles");
			return "Isosceles";
		}
		logger.debug("fetchTriangleType: Returned Triangle type : Scalene");
		return "Scalene";
	}

	/**
	 * To return merged and sorted array of integers
	 * 
	 * @param intArrayList
	 * @return Array of sorted integer
	 */
	public RestResponseModel mergeToAnArray(RestRequestModel request) {
		logger.debug("mergeToAnArray: Removal of duplicate, sorted and merged arrays");

		SortedSet<Integer> array = new TreeSet<Integer>();
		List<List<Integer>> list = new ArrayList<List<Integer>>(request.getValues().values());
		Iterator<List<Integer>> iterator = list.iterator();

		while (iterator.hasNext()) {
			array.addAll(iterator.next());
		}
		RestResponseModel resp = new RestResponseModel();
		resp.setArray(array.toArray());
		logger.debug("mergeToAnArray: Sorted and Merged arrays");
		return resp;

	}

}
