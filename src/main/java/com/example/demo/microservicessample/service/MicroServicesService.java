package com.example.demo.microservicessample.service;

import org.springframework.stereotype.Service;

@Service
public class MicroServicesService {
	
	/**
	 * To find the nth Fibonacci number
	 * 
	 * @param nthFibonacciNum
	 * @return nth fibonacci number
	 */
	public int getNthFibonacciNumber(long nthFibonacciNum) {
		// 0,1,2,3,5
		int num1 = 1;
		int num2 = 0;
		if(nthFibonacciNum<0) {
			throw new IllegalArgumentException ("Wrong input: " + nthFibonacciNum);
		}
		
		for (int i = 0; i <= nthFibonacciNum; i++) {
			int fibonacci = num1 + num2;
			num1 = num2;
			num2 = fibonacci;
		}

		return num1;
	}

	/**
	 * To reverse the given string in the sentence
	 * 
	 * @param sentence
	 * @return reversed string
	 */
	public String reverseWords(String sentence) {
		// how are you - woh era uoy
		String[] wordArray = sentence.split(" ");
		StringBuilder reversedStrBuilder = new StringBuilder();
		for (String word : wordArray) {
			StringBuilder reversedWord = new StringBuilder();
			for (int i = word.length() - 1; i >= 0; i--) {
				reversedWord.append(word.charAt(i));
			}
			reversedStrBuilder.append(reversedWord).append(" ");
		}
		return reversedStrBuilder.toString();
	}

	/**
	 * To return Three types of triangle - Equilateral, Isosceles or Scalene
	 * @param sideDimension1
	 * @param sideDimension2
	 * @param sideDimension3
	 * @return type of triangle as a String value
	 */
	public String fetchTriangleType(int sideDimension1, int sideDimension2, int sideDimension3) {

		if (sideDimension1 == sideDimension2 && sideDimension2 == sideDimension3) {
			return "Equilateral";
		}
		if (sideDimension1 == sideDimension2 || sideDimension2 == sideDimension3 || sideDimension1 == sideDimension3) {
			return "Isosceles";
		}
		return "Scalene";
	}
	

}
