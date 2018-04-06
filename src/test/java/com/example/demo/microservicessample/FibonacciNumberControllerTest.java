package com.example.demo.microservicessample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.microservicessample.controller.FibonacciNumberController;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FibonacciNumberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private FibonacciNumberController fibonacciNumberController;
	@InjectMocks
	private MicroServicesService microServicesService;

	private String n = "10";

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(fibonacciNumberController).build();
	}

	@Test
	public void testGetNthFibonacciNumberPositive() throws Exception {
		given(this.fibonacciNumberController.getNthFibonacciNumber("10")).willReturn(ResponseEntity
				.status(HttpStatus.OK).cacheControl(CacheControl.noCache()).header("Pragma", "no-cache").body("55"));
		mockMvc.perform(get("/api/Fibonacci").param("n", n)).andExpect(status().isOk());
	}

	@Test
	public void testFibonacciNumberPositive() {
		assertEquals(55, microServicesService.getNthFibonacciNumber(10));
	}

	@Test
	public void testFibonacciNumberNegative() {
		assertNotEquals(122, microServicesService.getNthFibonacciNumber(10));
	}

	@Test
	public void testGetNthFibonacciNumberException() throws Exception {
		when(fibonacciNumberController.getNthFibonacciNumber("10")).thenThrow(new NumberFormatException());
	}

	@Test
	public void testFibonacciSuccess() throws Exception {
		mockMvc.perform(get("/api/Fibonacci").param("n", n)).andExpect(status().isOk());
	}

}
