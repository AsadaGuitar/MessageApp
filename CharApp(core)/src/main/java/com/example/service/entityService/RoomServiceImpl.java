package com.example.service.entityService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.entity.Room;
import com.example.repository.RoomRepository;
import com.example.service.interfaces.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public List<Room> findByAccounts(Account account) {
		return roomRepository.findByAccounts(account);
	}

	@Override
	public Optional<Room> findOne(Integer id) {
		return roomRepository.findById(id);
	}
	
	@Override
	public Room create(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room update(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public void delete(Integer id) {
		roomRepository.deleteById(id);
	}
	

}
