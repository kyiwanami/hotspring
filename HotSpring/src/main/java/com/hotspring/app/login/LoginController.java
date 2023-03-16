package com.hotspring.app.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotspring.domain.model.UserBean;
import com.hotspring.domain.service.AwsService;
import com.hotspring.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

	@Autowired
	private AwsService awsService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserBean userBean;

	@GetMapping(value = "/init")
	public String init(Model model) {

		model.addAttribute("loginForm", new LoginForm());

		return "login/login";
	}

	@PostMapping(value = "/auth")
	public String login(Model model, LoginForm loginForm) throws Exception {

		// TODO: ユーザー認証
		String sub = "";

		// ユーザー取得
		String userName = userService.getUser(sub);
		userBean.setUserName(userName);

		return "top/top";
	}
}
