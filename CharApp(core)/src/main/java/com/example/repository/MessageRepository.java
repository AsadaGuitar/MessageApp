package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Message;
import com.example.domain.entity.Room;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

	List<Message>findByRoom(Room room);
}
