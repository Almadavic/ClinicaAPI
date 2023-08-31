package com.almada.clinicaapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class ClinicaApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void findPageDentists() throws Exception {

		mockMvc.perform(get("/qualquer"))
				.andExpect(status().isOk());

	}

}
