package com.example.service.interfaces;

import java.util.List;

import com.example.domain.entity.Message;
import com.example.domain.entity.Room;

public interface MessageService {

	public List<Message>findByRoom(Room room);
	
	public Message create(Message message);
	
	public Message update(Message message);
	
	public void delete(Integer id);
}
