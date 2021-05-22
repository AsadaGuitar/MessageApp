package com.example.service.entityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.entity.RoomPlace;
import com.example.repository.RoomPlaceRepository;
import com.example.service.interfaces.RoomPlaceService;

@Service
public class RoomPlaceServiceImpl implements RoomPlaceService{
	
	@Autowired
	RoomPlaceRepository roomPlaceRepository;
	
	@Override
	public List<RoomPlace> findByAccounts(Account account) {
		return roomPlaceRepository.findByAccounts(account);
	}

	@Override
	public RoomPlace create(RoomPlace roomPlace) {
		return roomPlaceRepository.save(roomPlace);
	}

	@Override
	public RoomPlace update(RoomPlace roomPlace) {
		return roomPlaceRepository.save(roomPlace);
	}

	@Override
	public void delete(Integer id) {
		roomPlaceRepository.deleteById(id);
	}

}
