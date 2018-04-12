package com.example.demo.microservicessample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.microservicessample.controller.TriangleTypeController;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TriangleTypeControllerTest {
	private int a,b,c =1;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TriangleTypeController triangleTypeController;

	@InjectMocks
	private MicroServicesService microServicesService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(triangleTypeController).build();
	}

	@Test
    public void testTriangleTypeApi() throws Exception {
		triangleTypeController.fetchTriangleType(a, b, c)
		.getStatusCode().compareTo(HttpStatus.OK);
		
	}
	
	
	@Test
	public void testTriangleTypeSuccess() throws Exception {
		mockMvc.perform(get("/api/TriangleType").param("a", "1").param("b", "1").param("c", "1"))
				.andExpect(status().is(200));
	}

	@Test
	public void testTriangleTypeControllerPositive() throws Exception {
		mockMvc.perform(get("/api/TriangleType").param("a", "1").param("b", "1").param("c", "1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testTriangleTypeEquilateralPositive() {
		assertEquals("Equilateral", microServicesService.fetchTriangleType(1, 1, 1));
		assertNotEquals("Equilateral", microServicesService.fetchTriangleType(1, 2, 3));
	}

	@Test
	public void testTriangleTypeIsoscelesPositive() {
		assertEquals("Isosceles", microServicesService.fetchTriangleType(1, 1, 4));
		assertEquals("Isosceles", microServicesService.fetchTriangleType(1, 4, 4));
		assertEquals("Isosceles", microServicesService.fetchTriangleType(4, 6, 4));
		assertNotEquals("Isosceles", microServicesService.fetchTriangleType(4, 3, 1));
	}

	@Test
	public void testTriangleTypeScalenePositive() {
		assertEquals("Scalene", microServicesService.fetchTriangleType(3, 1, 4));
		assertNotEquals("Scalene", microServicesService.fetchTriangleType(1, 1, 1));
	}
	
	@Test
    public void testFibonacciApiInvalid() throws Exception {
       try{
    	   triangleTypeController.fetchTriangleType(new String("abc"),new String("abc"),new String("abc"));
       }catch(NumberFormatException e){
    	   
       }
	}

}
