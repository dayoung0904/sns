package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.Entity.UserEntity;
import com.sns.user.bo.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	/**
	 * 로그인 아이디 중복 확인 API
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {

		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 응답값 만들고 리턴 => JSON
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		
		if(user == null) {
			// 중복 아님
			result.put("isDuplicated", false);
		} else {
			// 중복
			result.put("isDuplicated", true);
		}
		
		return result;
	}
	
	/**
	 * 회원가입 API
	 * 
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("email") String email) {

		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);

		// db insert
		Integer userId = userBO.addUser(loginId, hashedPassword, name, email);

		Map<String, Object> result = new HashMap<>();
		if (userId != null) {
			// 응답
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패했습니다.");
		}

		return result;
	}
	
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);

		// db 조회
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		// 결과
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			// 로그인
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			// 로그인 불가
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자 입니다.");
		}
		
		return result;
	}
}
