package com.EvaExchange.demo.repository;

import com.EvaExchange.demo.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
