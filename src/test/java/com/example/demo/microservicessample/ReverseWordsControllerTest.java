package com.example.demo.microservicessample;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.microservicessample.controller.ReverseWordsController;
import com.example.demo.microservicessample.service.MicroServicesService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReverseWordsControllerTest {
	
	private String sentence = "How are you";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ReverseWordsController reverseWordsController;
	
	@InjectMocks
	private MicroServicesService microServicesService;
	
	@Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reverseWordsController)
                .build();
    }
	
	@Test
	public void testSuccessfulWordsReversal() throws Exception {
		mockMvc.perform(get("/api/ReverseWords").param("sentence", sentence))
		.andExpect(status().isOk());
	}
} 