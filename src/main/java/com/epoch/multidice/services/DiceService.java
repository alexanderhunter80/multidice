package com.epoch.multidice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epoch.multidice.models.DiceRoller;
import com.epoch.multidice.models.RollEvent;
import com.epoch.multidice.repositories.RollEventRepository;

@Service
public class DiceService {
	
	@Autowired
	private RollEventRepository rolls;
	
	public DiceService() {}
	
	public RollEvent createRollEvent(RollEvent rollEvent) {
		DiceRoller roller = new DiceRoller(rollEvent);
		rollEvent = roller.roll();
		rolls.save(rollEvent);
		rollEvent.report();
		return rollEvent;
	}
	
	public RollEvent getEventById(Long id) {
		Optional<RollEvent> opt = rolls.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	

}
