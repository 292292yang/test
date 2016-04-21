/**
 * 
 */
package com.springmvc4maven.web.users;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc4maven.dao.users.UserMapper;
import com.springmvc4maven.domain.users.Authenticate;
import com.springmvc4maven.domain.users.User;

/**
 * @author TrueLiteTrace
 *
 */
@Controller
@RequestMapping("/users") // 대표 url
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	// User Dao와 DI 관계 설정
	@Autowired
	private UserMapper userDao;

	@RequestMapping("/form")
	// Get 방식, spring model.ui와 form.jsp file과 mapping, Data를 JSP에 전달하는 역할
	public String creatForm(Model model) {
		// form.jsp에 modelAttribute와 연동
		model.addAttribute("user", new User());
		return "users/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	// validation check
	public String create(@Valid User user, BindingResult bindingResult) {
		log.debug("User : {}", user);
		// 에러가 발생할 경우 users/form 페이지로 이동
		if (bindingResult.hasErrors()) {
			log.debug("Binding Result has error !!!");

			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				log.debug("error : {}, {}", error.getCode(), error.getDefaultMessage());
			}
			return "users/form";
		}

		// Create new user to DB
		userDao.create(user);
		// 입력한 데이타를 셀렉트 하여 확인
		log.debug("Database : {}", userDao.findById(user.getUserId()));
		// return "users/form";
		// 정상적으로 입력 되었을 경우, root 페이지로 이동
		return "redirect:/";
	}
	// 사용자 정보 업데이트 화면
	@RequestMapping("{userId}/form") // 사용자 ID는 수시로 바뀌기 때문에 사전에 세팅
	public String updateForm(@PathVariable String userId, Model model) {
		// userId가 null인 경우 에러 처리
		if(userId==null) {
			throw new IllegalArgumentException("사용자 아이디가 필요");
		}
		
		User user = userDao.findById(userId);
		// form.jsp에 modelAttribute와 연동
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	// validation check
	public String update(@Valid User user, BindingResult bindingResult, HttpSession session) {
		log.debug("User : {}", user);
		// 에러가 발생할 경우 users/form 페이지로 이동
		if (bindingResult.hasErrors()) {
			log.debug("Binding Result has error !!!");

			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				log.debug("error : {}, {}", error.getCode(), error.getDefaultMessage());
			}
			return "users/form";
		}
		
		Object temp = session.getAttribute("userId");
		if(temp == null) {
			throw new NullPointerException();
		}
		
		// Binding
		String userId = (String)temp;
		// 세션에 있는 userId와 조회한 userId가 같은지 반드시 체크
		if(!user.matchUserId(userId)) {
			throw new NullPointerException();
		}

		// Create new user to DB
		userDao.update(user);
		// 입력한 데이타를 셀렉트 하여 확인
		log.debug("Database : {}", userDao.findById(user.getUserId()));
		// return "users/form";
		// 정상적으로 입력 되었을 경우, root 페이지로 이동
		return "redirect:/";
	}
	
	

	// login page
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		model.addAttribute("authenticate", new Authenticate());
		return "users/login";
	}

	// login page Test
	@RequestMapping("/login")
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, HttpSession session, Model model) {
		if(bindingResult.hasErrors()) {
			return "users/login";
		}
		
		// 실질적인 Biz logic
		User user = userDao.findById(authenticate.getUserId());
		if(user == null) {
			// TODO 에러처리 - 존재하지 않는 사용자
			// 사용자 에게 에러 메시지 전달
			model.addAttribute("errorMessage", "존재하지 않는 사용자 !!!");
			return "users/login";
		}
		
		if(user.getPassword().equals(authenticate.getPassword())) {
			// TODO 에러처리 - 비밀 번호 오류
		}
		
		// User.java matchPassword 추가 후 생성
		if(!user.matchPassword(authenticate)) {
			model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
			return "users/login";
		}
		
		// session에 사용자 정보 저장
		session.setAttribute("userId", user.getUserId());
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
	
}








