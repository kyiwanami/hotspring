package com.hotspring.app.top;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

	@GetMapping(value = "top")
	public String init() {
		return "top/top";
	}
}
