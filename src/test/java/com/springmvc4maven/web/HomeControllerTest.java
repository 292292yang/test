package com.springmvc4maven.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

public class HomeControllerTest {
	
	// Spring mvc environment test
	@Test
	public void home() throws Exception{
		standaloneSetup(new HomeController()).build()
			.perform(get("/"))
			// .andExpect(status().isOk());
			.andExpect(status().isOk())
	// home.jsp로 정상적으로 이동하는지를 test: RedirectControllerTests.java file 참조(그러나 POST 방식, 여기선 GET 방식을 사용) 
			.andExpect(forwardedUrl("home"));
	}

}
