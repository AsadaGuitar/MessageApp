package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Friends;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer>{

	@Query(nativeQuery=true, value="select * from friends where account_id = :user order by date desc limit 5")
	List<Friends> findAllByNewDate(@Param("user") Integer id);
	
	@Query(nativeQuery=true, value="select * from friends where account_id = :user")
	List<Friends> findAllByAccountId(@Param("user") Integer id);
}
