package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

	@Query(nativeQuery=true, 
			value="select * from message where to_user = :user order by date desc limit 5")
	List<Message> findAllByNewDateFromToUser(@Param("user") String user);
	
	@Query(nativeQuery=true, 
			value="select * from message where from_user = :user order by date desc limit 5")
	List<Message> findAllByNewDateFromUser(@Param("user") String user);
}
