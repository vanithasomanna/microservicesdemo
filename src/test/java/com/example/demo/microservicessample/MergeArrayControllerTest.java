package com.example.demo.microservicessample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.microservicessample.model.RestRequestModel;
import com.example.demo.microservicessample.model.RestResponseModel;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MergeArrayControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MicroServicesService microServicesService;

	RestResponseModel mockOneArrayResponse = new RestResponseModel(new Object[] { 0, 1, 2, 3, 6, 7, 8, 9, 23, 45, 90 });
	String mockOneArrayRequest = "{\"Array1\":[1,2,3,45],\"Array2\":[7,9,0,6,8,23],\"Array3\":[90,23,1,2,3]}";

	@Test
	public void testMergeArrayServicePositive() throws Exception {
		Mockito.when(microServicesService.mergeToAnArray(Mockito.any(RestRequestModel.class)))
				.thenReturn(mockOneArrayResponse);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/MakeOneArray").accept(MediaType.APPLICATION_JSON)
				.content(mockOneArrayRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("{\"Array\":[0,1,2,3,6,7,8,9,23,45,90]}"));
	}

}
