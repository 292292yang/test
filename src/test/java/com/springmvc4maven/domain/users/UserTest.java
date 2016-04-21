package com.springmvc4maven.domain.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	
	private static Validator validator;
	
	@BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	// If userId value is empty
	@Test
	public void userIdWhenIsEmpty() {
		User user = new User("", "password", "name", "Hwarang@korenaknights.com");
		
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		assertThat(constraintViolations.size(), is(2));
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			log.debug("Violation error message: {}", constraintViolation.getMessage());
		}
	}
	/* Password가 사용자 입력정보와 DB 정보가 같은지 테스트
	@Test
	public void matchPassword() throws Exception {
		// userId와 password가 같을 경우
		String password = "password";
		Authenticate authenticate = new Authenticate("userId", password);
		User user = new User("userId", password, "name", "hwarang@koreanknights.com");
		boolean actual = user.matchPassword(authenticate);
		assertTrue(actual);
	}
	*/
	// Password가 사용자 입력정보와 DB 정보가 다른지 테스트
		@Test
		public void matchPassword() throws Exception {
			// userId와 password가 같을 경우
			String password = "password";
			Authenticate authenticate = new Authenticate("userId", password);
			User user = new User("userId", password, "name", "hwarang@koreanknights.com");
			assertTrue(user.matchPassword(authenticate));
			
			authenticate = new Authenticate("userId", "password2");
			assertFalse(user.matchPassword(authenticate));
		}
	
}
