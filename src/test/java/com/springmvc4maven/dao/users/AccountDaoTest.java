package com.springmvc4maven.dao.users;

import com.springmvc4maven.domain.users.AccountDO;
import com.springmvc4maven.domain.users.User;
import com.springmvc4maven.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class AccountDaoTest {
	
	private static final Logger log = LoggerFactory.getLogger(AccountDaoTest.class);
	
	@Resource
	private AccountMapper accountMapper;

	@Resource
	private UserService userService;

	@Test
	public void create(){
		AccountDO accountDO=new AccountDO();
		accountDO.setName("zhangsan");
		accountDO.setNum(2000);
		accountMapper.create(accountDO);
	}

	@Test
	public void transfer(){
		try{
			userService.transfer(1L,2L,1000);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void queryUser(){
		AccountDO accountDO=userService.queryUser(1L);
		System.out.println("acountDo="+accountDO);
	}
}
