package com.example.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.entity.Account;
import com.example.domain.form.AccountForm;
import com.example.domain.session.MailPassword;
import com.example.service.interfaces.AccountService;
import com.example.service.interfaces.MailService;
import com.example.service.interfaces.RoomService;

@Controller
@RequestMapping(path="/")
@SessionAttributes(names="createdAccountForm")
public class LoginController {
	
	@Autowired 
	MailService mailService;
	
	@Autowired 
	AccountService accountService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	MailPassword mailPassword;
	
	
	@ModelAttribute
	AccountForm setAccountForm() {
		return new AccountForm();
	}
	
	
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
		return "login/signup/SignupForm";
	}
	
	
	/**
	 * 入力されたAccountFormの受け取り
	 * 確認メールの送信
	 * 確認フォーム画面への遷移
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path="/signup")
	public String viewMailPass(@Validated AccountForm accountForm,
			BindingResult result, Model model) {
		
		System.out.println(result.getAllErrors());
		
		if(result.hasErrors()) 
			return "login/signup/SignupForm";
		
		/**
		 * パスワードの送信
		 * 送信したパスワードの取得
		 */
		String password;
		try {
			password = mailService.sendMessage(accountForm);
		}
		catch(Exception e) {
			System.out.println("mailService is fail");
			return "login/signup/SiginupForm";
		}
		
		//セッションに保存
		mailPassword.setPassword(password);
		
		//セッションに保存
		model.addAttribute("createdAccountForm", accountForm);
		
		return "login/signup/Confirmation";
	}
	
	/**
	 * 確認メールフォームの受け取り
	 * メールフォームの確認
	 * アカウント作成のアナウンス画面への遷移
	 * 
	 * @param mailPass
	 * @param model
	 * @return
	 */
	@PostMapping(path="/confirmation")
	public String viewSetPassword(
			@RequestParam("userPassword") String userPassword,
			@ModelAttribute("createdAccountForm") AccountForm accountForm,
			Model model) {
		
		//パスワードが合致しているか
		if(!mailService.authenticateMailPassword(
				userPassword, mailPassword.getPassword())) {
			
			model.addAttribute("confirmationError",true);
			return "login/signup/Confirmation";
		}
	
		//AccountFormを元に作成
		Account account = accountService.createByAccountForm(accountForm);
		System.out.println(account);
		Account findAccount = accountService.findOne(account.getId()).get();
		System.out.println(findAccount);
		
		//作成したユーザーのIDをモデルに詰める
		model.addAttribute("userId", account.getId());
		System.out.println("go");
		return "login/signup/CreatedAccount";
	}
	

	
}
