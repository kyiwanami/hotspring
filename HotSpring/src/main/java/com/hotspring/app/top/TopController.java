package com.hotspring.app.top;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotspring.domain.service.HotSpringService;
import com.hotspring.entity.MHotSpringEntity;

@Controller
public class TopController {

	@Autowired
	private HotSpringService hotSpringService;
	
	@GetMapping(value = "top")
	public String init(Model model) {
		
		List<MHotSpringEntity> hotSpringList = 
				hotSpringService.selectHotspring();
		
		model.addAttribute("hotSpringList", hotSpringList);
		
		return "top/top";
	}
}
