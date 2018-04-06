package com.example.demo.microservicessample;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.microservicessample.controller.TriangleTypeController;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TriangleTypeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private TriangleTypeController triangleTypeController;

	@InjectMocks
	private MicroServicesService microServicesService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(triangleTypeController).build();
	}

	@Test
	public void testTriangleTypeSuccess() throws Exception {
		mockMvc.perform(get("/api/TriangleType").param("a", "1").param("b", "1").param("c", "1"))
				.andExpect(status().is(200));
	}

	@Test
	public void testTriangleTypeControllerPositive() throws Exception {
		given(this.triangleTypeController.fetchTriangleType("1", "1", "1"))
				.willReturn(ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache())
						.header("Pragma", "no-cache").body("Equilateral"));
		mockMvc.perform(get("/api/TriangleType").param("a", "1").param("b", "1").param("c", "1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testTriangleTypeEquilateralPositive() {
		assertEquals("Equilateral", microServicesService.fetchTriangleType(1, 1, 1));
	}

	@Test
	public void testTriangleTypeIsoscelesPositive() {
		assertEquals("Isosceles", microServicesService.fetchTriangleType(1, 1, 4));
	}

	@Test
	public void testTriangleTypeScalenePositive() {
		assertEquals("Scalene", microServicesService.fetchTriangleType(3, 1, 4));
	}
}
