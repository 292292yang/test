/*
 * MyBatis기반의  UserDao Test.
 */

package com.springmvc4maven.dao.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springmvc4maven.domain.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class MyBatisUserDaoTest {

	// DB에 실제로 data가 담겨있는지 확인
	private static final Logger log = LoggerFactory.getLogger(MyBatisUserDaoTest.class);

	@Autowired
	private UserMapper userDao;

	@Test
	public void findById() {
		User user = userDao.findById("Hwarang");
		log.debug("User: {}", user);
	}
	
	@Test
	public void create() throws Exception {
		User user = new User("Alba", "password", "actor", "alba@gmail.com");
		userDao.create(user);
		// User dbUSer = userDao.findById(user.getUserId());
		
		// DB에 새로 추가한 user와 조회한 user가 동일한지를 JUnit을 이용하여 테스트 
		// 이대로 테스트 하면 error 발생 --> User.java에 equals method를 추가해야 error발생 하지 않는다.
		User actual = userDao.findById(user.getUserId());
		assertThat(actual, is(user));
	}
}
