package com.example.demo.microservicessample;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.microservicessample.controller.MergeArrayController;
import com.example.demo.microservicessample.model.IntArrayList;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MergeArrayControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MergeArrayController mergeArrayController;
	@InjectMocks
	private MicroServicesService microServicesService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(mergeArrayController).build();
	}

	@Test
	public void testMergeToArrayPositive() throws Exception {
		IntArrayList intArrayList = new IntArrayList();
		List<Integer> array1 = new ArrayList<>();
		array1.add(1);
		array1.add(2);
		array1.add(3);
		List<Integer> array2 = new ArrayList<>();
		array2.add(3);
		array2.add(4);
		array2.add(5);
		intArrayList.setArray1(array1);
		intArrayList.setArray2(array2);

		List<Integer> arrays = new ArrayList<>();
		arrays.add(1);
		arrays.add(2);
		arrays.add(3);
		arrays.add(4);
		arrays.add(5);

		mockMvc.perform(post("/api/MakeOneArray").requestAttr("Array1", array1).requestAttr("Array2", array2))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testMergeArrayServicePositive() {
		IntArrayList intArrayList = new IntArrayList();
		List<Integer> array1 = new ArrayList<>();
		array1.add(1);
		array1.add(2);
		array1.add(3);
		List<Integer> array2 = new ArrayList<>();
		array2.add(3);
		array2.add(4);
		array2.add(5);
		intArrayList.setArray1(array1);
		intArrayList.setArray2(array2);
		intArrayList.setArray3(array1);
		intArrayList.setArray4(array2);
		intArrayList.setArray5(array1);
		intArrayList.setArray6(array1);
		intArrayList.setArray7(array1);
		intArrayList.setArray8(array1);
		intArrayList.setArray9(array1);
		intArrayList.setArray10(array1);

		List<Integer> arrays = new ArrayList<>();
		arrays.add(1);
		arrays.add(2);
		arrays.add(3);
		arrays.add(4);
		arrays.add(5);

		intArrayList.setArrays(arrays);

		IntArrayList mergedArray = microServicesService.mergeToAnArray(intArrayList);
		assertEquals(intArrayList.getArrays(), mergedArray.getArrays());
	}

}
