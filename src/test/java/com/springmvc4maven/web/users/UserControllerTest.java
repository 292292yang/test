package com.springmvc4maven.web.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springmvc4maven.dao.users.JdbcUserDao;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	private JdbcUserDao userDao;
		
	// Database와 연동하지 않으면서 UserController와 UserDao class 연결하여 instance를 생성하여 사용할 수 있다.
	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;
	/*
	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(new UserController()).alwaysExpect(status().isMovedTemporarily()).build();
	}
	*/
	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(userController).build();
	}

	@Test
	public void createWhenValid() throws Exception {
		// FormControllerTests.java 참조
		this.mockMvc.perform(
				post("/users")
				.param("userId", "hwarang")
				.param("password", "password")
				.param("name", "koreanknights")
				.param("email", ""))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void createWhenInValid() throws Exception {
		// FormControllerTests.java 참조
		this.mockMvc.perform(
				post("/users")
				.param("userId", "Hwarang")
				.param("password", "password")
				.param("name", "koreanknights")
				.param("email", "Hwarang"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("users/form"));
	}
}
