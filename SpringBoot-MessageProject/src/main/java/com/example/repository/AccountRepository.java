package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

}