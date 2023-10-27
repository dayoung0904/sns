package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;

@Controller
public class testController {


	@Autowired
	PostMapper postMapper;
	
	// 1. string + response body -> html
	@ResponseBody
	@GetMapping("/test5")
	public String test5() {
		return "Hello world";
	}
	
	
	// 2. map + response body -> json
	@ResponseBody
	@GetMapping("/test6")
	public Map<String, Object> test6(){
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		
		return map;
	}
	
	// 3. JSP -> html
	// build.gradle 2줄 추가
	// 폴더 만들기 src/main/webapp/WEB-INF/jsp
	// application.yml => prefix, suffix
	@GetMapping("/test7")
	public String test7() {
		return "test/test";
	}
	
	
	// 4. DB 연동 + response body -> json
	// DB 설정 어노테이션 삭제
	// DatabaseConfig 클래스 추가
	// application.yml DB 접속 정보 추가
	
	// resources/mappers xml
	// logback-spring.xml 추가(쿼리 로그)
	@ResponseBody
	@GetMapping("/test8")
	public List<Map<String, Object>> test8(){
		return postMapper.selectPostList(); // json
	}
}
