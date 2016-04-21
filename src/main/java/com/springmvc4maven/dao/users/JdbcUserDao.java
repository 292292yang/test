/**
 * Database에 접근하기 로직에 대한 클래스
 */
package com.springmvc4maven.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.springmvc4maven.domain.users.User;

/**
 * @author EUNSOO
 *
 */
public class JdbcUserDao extends JdbcDaoSupport implements UserMapper {
	
	// DB initialize 확인 log
	private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);
	
	@PostConstruct			// Database initialize를 수행하는 annotation
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("spring4maven.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		// DB initialize 확인 log
		log.info("Database initialized success!");
	}
	
	// 정상적으로 DB에 초기 데이터(spring4maven.sql)가 생성되었는지 확인 하기 위한 메소드
	/* (non-Javadoc)
	 * @see com.springmvc4maven.dao.users.IUserDao#findById(java.lang.String)
	 */
	public User findById(String userId) {
		// 아래의 RowMapper 작성 후 작성
		String sql = "select * from USERS where userId = ?";
		
		// Spring jdbc에서 JDBC를 추상화한 API를 사용할 수 있다.
		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// User.java Object와 Mapping
				return new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email")
						);
			}
		};
		
		// SQL과 RowMapper를 전달
		// return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		// login에 대한 exception 처리
		try {
			return getJdbcTemplate().queryForObject(sql,  rowMapper, userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	// 사용자 추가 method
	/* (non-Javadoc)
	 * @see com.springmvc4maven.dao.users.IUserDao#create(com.springmvc4maven.domain.users.User)
	 */
	public void create(User user) {
		String sql = "insert into USERS values(?, ?, ?, ?)";
		// 가변인자에 의한 전달
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}
	
	// 사용자 정보 수정 Method
	/* (non-Javadoc)
	 * @see com.springmvc4maven.dao.users.IUserDao#update(com.springmvc4maven.domain.users.User)
	 */
	public void update(User user) {
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		getJdbcTemplate().update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
}
