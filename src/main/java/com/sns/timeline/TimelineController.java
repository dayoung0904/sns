package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.post.Entity.PostEntity;
import com.sns.post.bo.PostBO;

@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline/list-view")
	public String timelineView(Model model) {
		
		List<PostEntity> postList = postBO.getPost();
		
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}