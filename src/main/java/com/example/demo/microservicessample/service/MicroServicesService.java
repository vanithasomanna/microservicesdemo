package com.example.demo.microservicessample.service;

import org.springframework.stereotype.Service;

@Service
public class MicroServicesService {

	/**
	 * Fibonacci series starts with 0,1 To find the nth Fibonacci number
	 * 
	 * @param nthFibonacciNum
	 * @return nth fibonacci number
	 */
	public int getNthFibonacciNumber(long nthFibonacciNum) {
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

		return num1;
	}

	/**
	 * To reverse the given string in the sentence
	 * 
	 * @param sentence
	 * @return reversed string
	 */
	public String reverseWords(String sentence) {
		// Input and output - how are you - woh era uoy
		// Word array to split the input sentence
		String[] wordArray = sentence.split(" ");
		// String builder to hold the reversed string builder
		StringBuilder reversedStrBuilder = new StringBuilder();
		// Loop the sentence entered word by word
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
	 * 
	 * @param sideDimension1
	 * @param sideDimension2
	 * @param sideDimension3
	 * @return type of triangle as a String value
	 */
	public String fetchTriangleType(int sideDimension1, int sideDimension2, int sideDimension3) {

		/*
		 * check for Equilateral triangle whose all sides are equal, else check for
		 * Isosceles triangle which has two equal sides else it will return Scalene
		 * where all three sides are not equal
		 */
		if (sideDimension1 == sideDimension2 && sideDimension2 == sideDimension3) {
			return "Equilateral";
		} else if (sideDimension1 == sideDimension2 || sideDimension2 == sideDimension3
				|| sideDimension1 == sideDimension3) {
			return "Isosceles";
		}
		return "Scalene";
	}

}
