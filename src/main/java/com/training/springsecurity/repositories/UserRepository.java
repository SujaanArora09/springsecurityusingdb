package com.training.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.springsecurity.entities.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public Optional<User> findById(Long id);
	
	public Optional<User> findByEmail(String email);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
}
