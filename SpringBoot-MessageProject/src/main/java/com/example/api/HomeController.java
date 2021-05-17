package com.example.api;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.entity.Account;
import com.example.domain.entity.Friends;
import com.example.domain.entity.Message;
import com.example.domain.form.MessageForm;
import com.example.domain.session.FriendsMessages;
import com.example.domain.session.UserAccount;
import com.example.service.AccountServiceImpl;
import com.example.service.FriendsServiceImpl;
import com.example.service.MessageServiceImpl;
import com.example.service.interfaces.AccountService;
import com.example.service.interfaces.FriendsService;
import com.example.service.interfaces.MessageService;
import com.example.service.other.CreateDateTime;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UserAccount userAccount;
	
	@Autowired
	FriendsMessages friendsMessages;
	
	@Autowired
	AccountService accountService;

	@Autowired
	FriendsService friendsService;
	
	@Autowired
	MessageServiceImpl messageService;
	
	@Autowired
	CreateDateTime createDateTime;
	
	
	
	/**
	 * userAccountを更新
	 * userAccountのフレンドリストを更新
	 * 
	 * @return
	 */
	@ModelAttribute
	MessageForm setupForm() {
//		BeanUtils.copyProperties(accountService.findOne(userAccount.getId()), userAccount); 
//		userAccount.setFriendsList(friendsService.findAllById(userAccount.getId()));

		return new MessageForm();
	}
	
	/**
	 * メッセージリストとフレンドリストをuserAccountのIDを元に新規発行
	 * どちらとも数を制限して検索している。
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String viewHome(Model model) {
		model.addAttribute(userAccount);
//		model.addAttribute("messageList", messageService.findAllByToUserNewDate(userAccount.getId())); -----------修正
		model.addAttribute("friendsList", friendsService.findAll());
		model.addAttribute("friendsMessages", friendsMessages.getMessageList());
		return "user/Home";
	}
	
	/**
	 * 選択されたフレンドのメッセージをセッションに格納
	 * 
	 * @param friendsId
	 * @param model
	 * @return
	 */
	@GetMapping(path="/friends/{friendsId}")
	public String openFriends(@PathVariable String friendsId) {
		
		friendsMessages.setMessageList(messageService.findAllByFromUserNewDate(Integer.parseInt(friendsId)));
		
		return "redirect:/home";
	}
	
	
	/**
	 * Messageの追加
	 * 
	 * @param toStrId
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path="/messagePost")
	public String postMessage(
			@RequestParam("toUser") String toStrId, 
			@Validated MessageForm form,
			BindingResult result, Model model) {
		
		if(result.hasErrors())
			return "redirect:/home";
		
		//送信先として入力されたIDを持つアカウントの有無を確認
		Integer toId = Integer.parseInt(toStrId);
		if(friendsService.findOne(toId).orElse(null) == null)
			return "redirect:/home";
		
		//MessageFormの作成  -----------------検討
		form.setToUser(toId);
		form.setFromUser(userAccount.getId());
		Account toAccount = accountService.findOne(toId).get();
		form.setDate(createDateTime.getAllSlash());
		form.setToName(toAccount.getFullName());
		form.setFromName(userAccount.getFullName());
		
		//Messageの送信
		messageService.createByForm(form);
		
		//Friendsのアクティブを更新
		Friends friends = friendsService.findOne(toId).get();
		friends.setDate(createDateTime.getAllSlash());
		friendsService.update(friends);
		
		return "redirect:/home";
	}
	
	/**
	 * Friendsの追加
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(path="/friendsPost")
	public String postFriends(@RequestParam("id") String id) {
		System.out.println("method start");
		
		//数値かどうか
		char firstChar = id.charAt(0);
		if(firstChar == '0') 
			return "redirect:/home";
		
		//入力されたIDを持つユーザーの有無を確認
		int friends_id = Integer.parseInt(id);
		if(!accountService.isExist(friends_id))
			return "redirect:/home";
		System.out.println("success method 1");
		
		//ユーザーのFriendsListにIDを持つフレンドがいるかどうか
		if(friendsService.isExist(friends_id))
			return  "redirect:/home";
		System.out.println("success method 2");
		
		//Friendsを新規作成  -------------------------------修正
		Friends friends = new Friends();
		friends.setFriendsId(friends_id);
		friends.setDate(createDateTime.getAllSlash());
		friends.setAccount(accountService.findOne(userAccount.getId()).get());
		friends.setFriendsName(accountService.findOne(friends_id).get().getFullName());
		friendsService.create(friends);
		System.out.println("success method 3");
		
		//ユーザーのFriendsリストに追加
		List<Friends> userFriendsList = userAccount.getFriendsList();
		userFriendsList.add(friends);
		userAccount.setFriendsList(userFriendsList);
		System.out.println("success method 4");
		
		//ユーザーのアカウントを更新
		Account account = new Account();
		BeanUtils.copyProperties(userAccount, account);
		accountService.update(account);
		System.out.println("success method 5");

		return "redirect:/home";
	}
	
	
}
