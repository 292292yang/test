/**
 * 
 */
package com.springmvc4maven.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TrueLiteTrace
 *
 */
@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home() {
		log.debug("logback setting success !!!");
		return "home";		// return home.jsp file
	}

}
