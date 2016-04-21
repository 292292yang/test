/**
 * 
 */
package com.springmvc4maven.dao.users;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
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
public class MyBatisUserDaoImple implements UserMapper {

	// DB initialize 확인 log
	private static final Logger log = LoggerFactory.getLogger(MyBatisUserDaoImple.class);

	// sqlSession을 사용할 수 있도록 설정
	private SqlSession sqlSession;
	
	private DataSource dataSource;

	@PostConstruct // Database initialize를 수행하는 annotation
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("spring4maven.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
		// DB initialize 확인 log
		log.info("Database initialized success!");
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springmvc4maven.dao.users.UserDao#findById(java.lang.String)
	 */
	@Override
	public User findById(String userId) {
		return sqlSession.selectOne("UserMapper.findById", userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.springmvc4maven.dao.users.UserDao#create(com.springmvc4maven.domain.
	 * users.User)
	 */
	@Override
	public void create(User user) {
		sqlSession.insert("UserMapper.create", user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.springmvc4maven.dao.users.UserDao#update(com.springmvc4maven.domain.
	 * users.User)
	 */
	@Override
	public void update(User user) {
		sqlSession.update("UserMapper.update", user);
	}

}
