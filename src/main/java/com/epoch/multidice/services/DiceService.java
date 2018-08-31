package com.epoch.multidice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epoch.multidice.models.RollEvent;
import com.epoch.multidice.repositories.DiceRepository;
import com.epoch.multidice.tools.CommandParser;

@Service
public class DiceService {
	
	@Autowired
	private DiceRepository rolls;
	
	public DiceService() {}
	
	public RollEvent createRollEvent(RollEvent roll) {
		roll.setHandfuls(CommandParser.parseToHandfuls(roll.getInputstring()));
		// rollem
		rolls.save(roll);
		return roll;
	}
	
	
	
	

}
