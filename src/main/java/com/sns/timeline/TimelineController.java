package com.sns.timeline;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {

	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {
		//List<Post> postList = 
		
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}