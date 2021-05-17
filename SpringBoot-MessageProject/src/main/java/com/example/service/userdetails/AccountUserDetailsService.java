package com.example.service.userdetails;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.session.FriendsMessages;
import com.example.domain.session.UserAccount;

import com.example.service.interfaces.AccountService;
import com.example.service.interfaces.MessageService;

@Service
public class AccountUserDetailsService implements UserDetailsService{

	@Autowired
	UserAccount userAccount;
	
	@Autowired
	FriendsMessages friendsMessages;

	@Autowired
	AccountService accountService;
	
	@Autowired
	MessageService messageService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//ログインフォームで入力されたＩＤの一文字目が０だった場合エラーを返却
		char usernameFirst = username.charAt(0);
		if(usernameFirst == '0')
			throw new UsernameNotFoundException(username + " is not found.");
		
		//数値変換してアカウントを検索
		Integer id = Integer.parseInt(username);
		Optional<Account> account = accountService.findOne(id);
		
		//ログインフォームで入力されたＩＤを持つアカウントの有無を確認
		if(account.orElse(null) == null)
			throw new UsernameNotFoundException(username + " is not found.");
		
		//userAccountにログインされたデータを登録
		var accountUserDetails = new AccountUserDetails(account.get(), getAuthorities(account.get()));
		BeanUtils.copyProperties(accountUserDetails.getAccount(), userAccount);
		userAccount.setAuthorities(accountUserDetails.getAuthorities());
		
		//FriendsMessagesの登録
		friendsMessages.setMessageList(messageService.findAllByToUserNewDate(id));
		
		return accountUserDetails;
	}
	
	
	private Collection<GrantedAuthority> getAuthorities(Account account){
		
		if(account.isAdmin())
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		else
			return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	
	
}
