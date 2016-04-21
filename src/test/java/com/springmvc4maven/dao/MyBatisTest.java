/**
 * MyBatis를 Test하기 위한 Test Case
 */
package com.springmvc4maven.dao;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataSource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.springmvc4maven.domain.users.User;

/**
 * @author EUNSOO
 *
 */
public class MyBatisTest {
	
	private static final Logger log = LoggerFactory.getLogger(MyBatisTest.class);
	// 전역 필드
	// 생성 방법: 전역필드( constructor의 instance 클릭 후 마우르 오른쪽 버튼 --> Refactor --> Convert Local Variable to Field...)
	private SqlSessionFactory sqlSessionFactory;
	
	// session 초기화
	@Before
	public void setup() throws IOException {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// JDBC에서 connection과 같은 역할. 
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 소스코드 안에서 직접 DB를 초기화하기 위해 Spring에서 제공하는  JDBC를 활용하여 사용할 수 있다.
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("spring4maven.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		log.info("Database intialized success !!!");
	}

	private javax.sql.DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/springmvc4maven");
		dataSource.setUsername("sa");
		return dataSource;
	}

	@Test
	public void gettingStarted() throws Exception{
		
		/* SQL Session Factory --> jdk 1.7이전 버전
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.findById", "Hwarang");
			
		  /*
		   *  Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
		   *  UserMapper.xml의 namespace와 id를 조합하여 사용
			
			log.debug("User : {}", user);
			
		} finally {
		  session.close();
		}
		*/
		
		/* JDK 1.7 이 후 버전: finally를 쓰지 않아도 됨. */
		try (SqlSession session = sqlSessionFactory.openSession()){
			// UserMapper.xml에 있는 namespace와 ID를 조합하여 설정, namespace의 경우 어디에 mapping이 되는지 알수 있다.
			// default DB input data = Hwarang
			User user = session.selectOne("UserMapper.findById", "Hwarang");
			log.debug("User : {}", user);
		}
	}
	
	@Test
	public void insert() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = new User("Hwarang2", "password", "Hwarang2", "hwaran2@koreanknights.com");
			session.insert("UserMapper.create", user);
			User actual = session.selectOne("UserMapper.findById", user.getUserId());
			
			assertThat(actual, is(user));
		}
	}
}
