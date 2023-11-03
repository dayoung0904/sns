package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment")
@RestController
public class ConmentRestController {

	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content") String content,
			@RequestParam("postId") int postId,
			HttpSession session){
		
		// 지금 작성하고 있는 사용자 userId가 필요함 => session
		int userId = (int)session.getAttribute("userId");
		
		// DB 저장
		
		// 결과값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
