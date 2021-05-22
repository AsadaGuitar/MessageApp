package com.example.service.userdetails;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.entity.Account;
import com.example.domain.entity.Room;
import com.example.domain.session.AccountSession;
import com.example.domain.session.RoomSession;
import com.example.service.interfaces.AccountService;


@Service
public class AccountUserDetailsService implements UserDetailsService{

	@Autowired
	AccountSession userAccount;
	
	@Autowired
	RoomSession roomSession;

	@Autowired
	AccountService accountService;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		//ログインフォームで入力されたＩＤの一文字目が０だった場合エラーを返却
		char usernameFirst = username.charAt(0);
		if(usernameFirst == '0')
			throw new UsernameNotFoundException(username + " is not found.");
		
		//数値変換してアカウントを検索
		Integer id = Integer.parseInt(username);
		
		//ログインフォームで入力されたＩＤを持つアカウントの有無を確認
		if(accountService.findOne(id).orElse(null) == null)
			throw new UsernameNotFoundException(username + " is not found.");
				
		//AccountSessionを設定
		Account account = accountService.findOne(id).get();
		userAccount.setAccount(account);

		/*
		 * RoomSessionを設定
		 * 
		 * FriendListの一番先頭のFriendのIDを検索
		 * RoomPlaceからFriendのIDにマッチしたRoomのIDを検索
		 * RoomのIDからRoomを検索
		 * RoomをRoomSessionに登録
		 * 
		*/
		Integer friendId = account.getFriends().get(0).getFriendId();
		Integer roomId = account.getRoomPlaces().stream()
				.filter(x -> x.getFriendId() == friendId).map(x -> x.getRoomId()).findFirst().get();
		Room room = account.getRooms().stream().filter(x -> x.getId() == roomId).findFirst().get();
		roomSession.setRoom(room);

		System.out.println(account);
		//ログイン中のアカウントのロールを発行
		var accountUserDetails = new AccountUserDetails(account, getAuthorities(account));
				
		//SpringSecurity
		return accountUserDetails;
	}
	
	
	private Collection<GrantedAuthority> getAuthorities(Account account){
		
		if(account.isAdmin())
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		else
			return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	
	
}
