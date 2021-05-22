package com.example.service.entityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Message;
import com.example.domain.entity.Room;
import com.example.repository.MessageRepository;
import com.example.service.interfaces.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public List<Message> findByRoom(Room room) {
		return messageRepository.findByRoom(room);
	}

	@Override
	public Message create(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public Message update(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public void delete(Integer id) {
		messageRepository.deleteById(id);
	}

}
