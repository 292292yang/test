package com.springmvc4maven.dao.users;

import com.springmvc4maven.domain.users.User;

public interface UserMapper {

	// 정상적으로 DB에 초기 데이터(spring4maven.sql)가 생성되었는지 확인 하기 위한 메소드
	User findById(String userId);

	// 사용자 추가 method
	void create(User user);

	// 사용자 정보 수정 Method
	void update(User user);

}