package com.hotspring.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotspring.domain.service.AwsService;
import com.hotspring.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {

	@Autowired
	private AwsService awsService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserBean userBean;

	@GetMapping(value = "/init")
	public String init(Model model) {

		model.addAttribute("authForm", new AuthForm());

		return "auth/create";
	}

	@PostMapping(value = "/create")
	public String createUser(Model model, AuthForm authForm) throws Exception {

		// ユーザー登録
		awsService.createUser(authForm.getUserName(), authForm.getPassword());
		model.addAttribute("authForm", new AuthForm());
		log.info("ユーザー登録成功");

		userService.createUser(authForm.getUserName(), false);

		return "auth/create";
	}

	@PostMapping(value = "/auth")
	public String authUser(Model model, AuthForm authForm) throws Exception {

		// ユーザー認証
		awsService.authUser(authForm.getUserName(), authForm.getPassword());
		log.info("ユーザー認証成功");

		userBean.setUserName(authForm.getUserName());

		return "auth/create";
	}

}
