package com.epoch.multidice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epoch.multidice.models.RollEvent;
import com.epoch.multidice.models.User;

@Repository
public interface DiceRepository extends CrudRepository<RollEvent, Long>{
	
	Optional<RollEvent> findById(Long id);
	
	List<RollEvent> findAllByUser(User user);

}
