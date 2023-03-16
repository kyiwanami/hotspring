package com.hotspring.app.register.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.hotspring.domain.model.UserData;
import com.hotspring.domain.service.AwsService;
import com.hotspring.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/register/user")
@Slf4j
public class RegisterUserController {

	@Autowired
	private AwsService awsService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/init")
	public String init(Model model) {

		model.addAttribute("preForm", new RegisterUserPreForm());

		return "register/user/pre";
	}

	@PostMapping(value = "/regist")
	public String register(Model model, RegisterUserPreForm registerUserPreForm) throws Exception {

		UserData userData = new UserData();
		BeanUtils.copyProperties(registerUserPreForm, userData);

		try {

			// ユーザー登録
			String sub = awsService.adminCreateUser(userData);
			userData.setSub(sub);
			userService.createUser(userData);

		} catch (UsernameExistsException e) {

			// TODO: 業務エラーとして画面に出したい。ControllerAdviceと例外クラスを実装すべき。
			log.error("メールアドレスが既に登録されています。");
			throw e;
		}

		model.addAttribute("fullForm", new RegisterUserFullForm());
		return "register/user/full";
	}

	@PostMapping(value = "/auth")
	public String auth(Model model, RegisterUserPreForm registerUserPreForm) throws Exception {

		// TODO: 仮パスワードの再認証処理

		return "login/login";
	}
}
