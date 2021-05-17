package com.example.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.domain.entity.Message;
import com.example.domain.form.MessageForm;
import com.example.domain.session.UserAccount;
import com.example.repository.MessageRepository;
import com.example.service.interfaces.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	UserAccount userAccount;
	
	
	@Override
	public Optional<Message> findOne(Integer id) {
		return messageRepository.findById(id);
	}
	
	@Override
	public boolean isExist(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}
	
	@Override
	public List<Message> findAllByFromUserNewDate(Integer id){
		return messageRepository.findAllByNewDateFromUser(String.valueOf(id));
	}
	
	@Override
	public List<Message> findAllByToUserNewDate(Integer id){
		return messageRepository.findAllByNewDateFromToUser(String.valueOf(id));
	}
	
	@Override
	public Message create(Message message) {
		return messageRepository.save(message);
	}
	
	public Message createByForm(MessageForm form) {
		
		Message message = new Message();
		BeanUtils.copyProperties(form, message);
		return messageRepository.save(message);
	}
	
	@Override
	public Message update(Message message) {
		return messageRepository.save(message);
	}
	
	@Override
	public void  delete(Integer id) {
		messageRepository.deleteById(id);
	}

	
	
}

