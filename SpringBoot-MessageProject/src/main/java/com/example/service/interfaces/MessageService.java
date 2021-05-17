package com.example.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.domain.entity.Message;

public interface MessageService {

	public Optional<Message> findOne(Integer id);
	
	public boolean isExist(Integer id);
	
	public List<Message> findAll();
	
	public List<Message> findAllByFromUserNewDate(Integer id);
	
	public List<Message> findAllByToUserNewDate(Integer id);
	
	public Message create(Message Message);
	
	public Message update(Message Message);
	
	public void delete(Integer id);
}
