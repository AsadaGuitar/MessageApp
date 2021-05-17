package com.example.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.entity.Account;
import com.example.domain.form.SignupForm;
import com.example.service.AccountServiceImpl;
import com.example.service.MailServiceImpl;
import com.example.service.interfaces.AccountService;
import com.example.service.interfaces.MailService;


@Controller
@RequestMapping(path="/")
public class LoginController {
	
	@Autowired 
	SignupForm signupForm;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping(path="/loginForm")
	public String viewLoginForm() {
		return "login/LoginForm";
	}
	
	@GetMapping(path="/signinForm")
	public String viewSigninForm() {
		return "login/SigninForm";
	}
	
	@PostMapping(path="/signinForm/error")
	public String viewSigninFormError(Model model) {
		model.addAttribute("error", true);
		return "login/SigninForm";
	}
	
	@GetMapping(path="/signupForm")
	public String viewSignupForm(Model model) {
		model.addAttribute(new SignupForm());
		return "login/signup/SignupForm";
	}
	
	@PostMapping(path="/signup")
	public String viewMailPass(@Validated SignupForm form,
			BindingResult result, Model model) {
		
		System.out.println(result.getAllErrors());
		
		if(result.hasErrors()) 
			return "login/signup/SignupForm";
		
		try {
			mailService.sendMessage(
					form.getMail(), form.getLastName() + form.getFirstName());
		}
		catch(Exception e) {
			System.out.println("mailService is fail");
			return "login/signup/SiginupForm";
		}
		
		BeanUtils.copyProperties(form, signupForm);  //----------------------Scopeにしないでthymeleafでhidden
		
		System.out.println("from LoginController : " + form.getFirstNameF());
		
		return "login/signup/Confirmation";
	}
	
	@PostMapping(path="/confirmation")
	public String viewSetPassword(@RequestParam("password") String mailPass,
			Model model) {
		
		if(!mailService.authenticateMailPassword(mailPass)) {
			model.addAttribute("confirmationError", true);
			return "login/signup/Confirmation";
		}
			
//		Account account = accountService.createByForm(signupForm);　 --------------------修正
//		model.addAttribute("userId", String.valueOf(account.getId()));
		
		return "login/signup/CreatedAccount";
		
	}
	

	
}
