package com.hotspring.app.register;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotspring.app.auth.UserBean;
import com.hotspring.domain.model.HotSpringEvaluationData;
import com.hotspring.domain.service.HotSpringService;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private UserBean userBean;

	@Autowired
	private HotSpringService hotSpringService;

	@GetMapping(value = "/init")
	public String init(Model model) {

		model.addAttribute("registerForm", new RegisterForm());

		return "register/register";
	}

	@PostMapping(value = "/register")
	public String regist(@ModelAttribute("registerForm") @Validated RegisterForm oldForm, BindingResult bindingResult,
			Model model) throws IOException {

		if (bindingResult.hasErrors()) {
			return "register/register";
		}

		// DBへ登録
		HotSpringEvaluationData data = new HotSpringEvaluationData();
		BeanUtils.copyProperties(oldForm, data);
		data = hotSpringService.register(data);

		RegisterForm newForm = new RegisterForm();
		BeanUtils.copyProperties(data, newForm);

		model.addAttribute("registerForm", newForm);

		return "register/register";
	}
}
