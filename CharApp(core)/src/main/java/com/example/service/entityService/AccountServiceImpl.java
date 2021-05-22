package com.example.service.entityService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.entity.Friend;
import com.example.domain.entity.Message;
import com.example.domain.entity.Room;
import com.example.domain.entity.RoomPlace;
import com.example.domain.form.AccountForm;
import com.example.repository.AccountRepository;
import com.example.service.interfaces.AccountService;
import com.example.service.interfaces.FriendService;
import com.example.service.interfaces.MessageService;
import com.example.service.interfaces.RoomPlaceService;
import com.example.service.interfaces.RoomService;
import com.example.service.other.CreateDateTime;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	RoomPlaceService roomPlaceService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	FriendService friendService;
	
	@Autowired
	CreateDateTime createDateTime;
	

	@Override
	public Optional<Account> findOne(Integer id) {
		return accountRepository.findById(id);
	}
	
	@Override
	public boolean isExist(Integer id) {
		Optional<Account> account = findOne(id);
		if(account.orElse(null) == null)
			return false;
		
		return true;
	}
		
	@Override
	public List<Account> findAll(){
		return (List<Account>) accountRepository.findAll();
	}
	
	/**
	 * AccountFormを元にアカウントを作成
	 * 
	 * メモ
	 * 関連するEntityのリストを作成するときは
	 * Accountの新規作成時に追加しなければならない
	 * OneToManyではChild側がParentを参照し保存する
	 * 
	 * @param form
	 * @return
	 */
	@Override
	public Account createByAccountForm(AccountForm form) {
		
		//AccountFormをAccountに変換
		Account account = new Account();
		BeanUtils.copyProperties(form, account);
		
		//Accountの情報を追加(パスワードを変換して上書き)
		String password = passwordEncoder.encode(form.getPassword());
		account.setPassword(password);
		account.setEnabled(true);
		account.setAdmin(false);
					
		//Roomの新規作成（IDの発行）
		var room = new Room();
		Room createdRoom = roomService.create(room);
		
		/*
		 * ManyToOne
		 * 
		 * ChildであるMessageがParentであるRoomを保存する
		 */
		var message = new Message();
		message.setComment("MessageAppにようこそ！");
		message.setRoom(createdRoom);
		message.setDate(createDateTime.getAllSlash());
		messageService.create(message);
				
		//RoomPlaceの新規作成（IDの発行）
		var roomPlace = new RoomPlace();
		roomPlace.setFriendId(1);
		Integer roomId = createdRoom.getId();
		roomPlace.setRoomId(roomId);
		RoomPlace createdRoomPlace = roomPlaceService.create(roomPlace);
				
		//Accountの新規作成
		account.setRooms(Arrays.asList(createdRoom));
		account.setRoomPlaces(Arrays.asList(createdRoomPlace));		
		Account createdAccount = create(account);
		
		/*
		 * ManyToOne
		 * 
		 * ChildであるFriendがParentであるAccountを保存する
		 */
		var friend = new Friend();
		friend.setFriendId(1);
		friend.setAccount(createdAccount);//IDを持つアカウントを設定
		friendService.create(friend);

		return createdAccount;
	}
	
	public Account create(Account account) {
		return accountRepository.save(account);
	}
	
	@Override
	public Account update(Account account) {
		return accountRepository.save(account);
	}
	
	@Override
	public void delete(Integer id) {
		accountRepository.deleteById(id);
	}
}
