package com.epoch.multidice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epoch.multidice.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
    Optional<User> findByUsername(String username);
    
    Optional<User> findById(Long id);
    
    Optional<User> findByEmail(String email);
    
    List<User> findAll();
    
}