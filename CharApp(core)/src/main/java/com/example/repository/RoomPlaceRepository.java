package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Account;
import com.example.domain.entity.RoomPlace;

@Repository
public interface RoomPlaceRepository extends JpaRepository<RoomPlace, Integer>{

	List<RoomPlace> findByAccounts(Account account);
}
