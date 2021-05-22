package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Account;
import com.example.domain.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

	List<Room> findByAccounts(Account account);
}
