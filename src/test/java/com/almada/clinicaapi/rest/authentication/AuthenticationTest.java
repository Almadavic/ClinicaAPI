package com.almada.clinicaapi.rest.authentication;

import com.almada.clinicaapi.config.securityConfig.LoginData;
import com.almada.clinicaapi.service.customException.DatabaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	private final String path = "/auth";

	@Test
	void loginFailPasswordWrong() throws Exception {

		LoginData loginData = new LoginData("admin", "1234567");

		loginFail(loginData);

	}

	@Test
	void loginFailLoginWrong() throws Exception {

		LoginData loginData = new LoginData("adminn", "123456");

		loginFail(loginData);

	}

	@Test
	void loginFailAccountDisabled() throws Exception {

		LoginData loginData = new LoginData("inativo", "123456");

		loginFail(loginData);

	}

	@Test
	void loginOk() throws Exception {

		LoginData loginData = new LoginData("admin", "123456");

		mockMvc.perform(post(path)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(loginData)))
				.andExpect(status().isOk());

	}

	private void loginFail(LoginData loginData) throws Exception {

		mockMvc.perform(post(path)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(loginData)))
				.andExpect(status().is(400))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
				.andExpect(result -> assertEquals("Login and / or password is / are wrong | or your account is disabled"
						, result.getResolvedException().getMessage()));

	}

}
