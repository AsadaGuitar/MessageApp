package com.example.api;




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

import com.example.domain.entity.Account;
import com.example.domain.entity.Friend;
import com.example.domain.entity.Message;
import com.example.domain.entity.Room;
import com.example.domain.entity.RoomPlace;
import com.example.domain.form.AddFriendForm;
import com.example.domain.form.MessageForm;
import com.example.domain.session.AccountSession;
import com.example.domain.session.RoomSession;
import com.example.service.RoomSessionService;
import com.example.service.AccountSessionService;
import com.example.service.interfaces.AccountService;
import com.example.service.interfaces.FriendService;
import com.example.service.interfaces.MessageService;
import com.example.service.interfaces.RoomPlaceService;
import com.example.service.interfaces.RoomService;
import com.example.service.other.CreateDateTime;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired AccountSession userAccount;
	
	@Autowired RoomSession roomSession;
	
	@Autowired AccountSessionService accountSessionService;
	
	@Autowired	 RoomSessionService roomSessionService;
	
	@Autowired AccountService accountService;
	
	@Autowired RoomService roomService;
	
	@Autowired RoomPlaceService roomPlaceService;
	
	@Autowired MessageService messageService;
	
	@Autowired FriendService friendService;
	
	@Autowired CreateDateTime createDateTime;
	


	@ModelAttribute
	MessageForm setMessageForm(Model model) {
		return new MessageForm();
	}
	
	@ModelAttribute
	AddFriendForm setAddFriendForm(Model model) {
		return new AddFriendForm();
	}
	
	@GetMapping
	public String viewHome(Model model) {
		
		//UserAccount?????????
		userAccount.setAccount(accountService.findOne(
				userAccount.getAccount().getId()).get());
		
		//RoomSession?????????
		roomSession.setRoom(roomService.findOne(
				roomSession.getRoom().getId()).get());
		
//		System.out.println("viewHome : " + userAccount );
//		System.out.println("viewHome : " +  roomSession);
//		System.out.println(roomSessionService.getMessageList());
//		System.out.println("friendId " + userAccount.getAccount().getFriends().get(0).getFriendId());
		
		model.addAttribute("friend", roomSessionService.getFriendAccount());
		model.addAttribute("messages", roomSessionService.getMessageList());
		model.addAttribute("userAccount" ,userAccount); 
		model.addAttribute("friendList", accountSessionService.getFriendList());
		model.addAttribute("roomSession", roomSession);
		return "user/Home";
	}
	
	
	/**
	 * Room?????????
	 * 
	 * @param friendsId
	 * @param model
	 * @return "redirect:/home"
	 */
	@GetMapping(path="/friends/{friendsStrId}")
	public String openFriends(@PathVariable String friendsStrId, Model model) {
		
		Integer friendId = Integer.parseInt(friendsStrId);
		
		Room room;
		
		try {
			room = accountSessionService.getRoomByFriendId(friendId);
		}
		catch(Exception e) {
			System.out.println("ERROR by openFriends method");
			return "redirect:/home";
		}
		
		//RoomSession?????????
		roomSession.setRoom(room);
		
		//????????????????????????????????????
		model.addAttribute("openFriendsSwitch", true);
		
		return "redirect:/home";
	}
	
	/**
	 * Message?????????
	 * 
	 * @param toStrId
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path="/messagePost")
	public String postMessage(
			@Validated MessageForm form,
			@RequestParam("friendId") String friendStrId,
			BindingResult result, Model model) {
		
		if(result.hasErrors())
			return "redirect:/home";
		
		Integer friendId = Integer.parseInt(friendStrId);
		
		Room room;
		
		try {
			room = accountSessionService.getRoomByFriendId(friendId);
		}
		catch(Exception e) {
			System.out.println("error");
			return "redirect:/home";
		}
		
		//Message???????????????
		Message message = new Message();
		message.setId(friendId);
		message.setComment(form.getComment());
		message.setDate(createDateTime.getAllSlash());
		message.setRoom(room);
		messageService.create(message);
		
		return "redirect:/home";
	}
	
	/**
	 * Friends?????????
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(path="/friendsPost")
	public String postFriends(@Validated AddFriendForm form,
			BindingResult result, Model model) {
		
		if(result.hasErrors())
			return "redirect:/home";
		
		int friendId = Integer.parseInt(form.getFriendId());
		
		//???????????????ID???????????????????????????????????????
		if(!accountService.isExist(friendId))
			return "redirect:/home";
		
		Account user = userAccount.getAccount();
		
		//??????????????????????????????????????????????????????????????????ID?????????
		var  friend = new Friend();
		friend.setFriendId(friendId);
		friend.setAccount(user);
		friendService.create(friend);
		
		//Room??????????????????ID??????????????????
		var room = new Room();
		Room createdRoom = roomService.create(room);
		
		//RoomPlace???????????????(ID??????????????????
		var roomPlace = new RoomPlace();
		roomPlace.setFriendId(friendId);
		roomPlace.setRoomId(createdRoom.getId());
		RoomPlace createdRoomPlace = roomPlaceService.create(roomPlace);
		
		//?????????????????????????????????ID?????????Room???RoomPlace?????????
		user.getRooms().add(createdRoom);
		user.getRoomPlaces().add(createdRoomPlace);
		accountService.update(user);

		return "redirect:/home";
	}
	
	
}
