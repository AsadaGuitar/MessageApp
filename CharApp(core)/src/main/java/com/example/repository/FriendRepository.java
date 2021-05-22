package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Account;
import com.example.domain.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{

	List<Friend> findByAccount(Account account);
}
