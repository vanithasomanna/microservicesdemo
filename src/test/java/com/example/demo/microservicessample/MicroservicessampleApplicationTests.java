package com.example.demo.microservicessample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.microservicessample.controller.FibonacciNumberController;
import com.example.demo.microservicessample.controller.ReverseWordsController;
import com.example.demo.microservicessample.controller.TriangleTypeController;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroservicessampleApplicationTests {

	@Autowired
	MicroServicesService microServiceService;

	@Autowired
	private FibonacciNumberController fibonacciNumberController;
	@Autowired
	private ReverseWordsController reverseWordsController;
	@Autowired
	private TriangleTypeController triangleTypeController;

	@Test
	public void contextLoads() {
		assertNotNull(fibonacciNumberController);
		assertNotNull(reverseWordsController);
		assertNotNull(triangleTypeController);
		assertNotNull(microServiceService);
	}

	@Test
	public void test() {
		MicroservicessampleApplication.main(new String[] { "--spring.main.web-environment=false", });
	}
}
