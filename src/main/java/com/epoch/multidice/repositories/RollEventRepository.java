package com.epoch.multidice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epoch.multidice.models.RollEvent;
import com.epoch.multidice.models.User;

@Repository
public interface RollEventRepository extends CrudRepository<RollEvent, Long>{
	
	List<RollEvent> findAll();
	
	Optional<RollEvent> findById(Long id);
	
	Optional<RollEvent> findByUser(User user);

}
