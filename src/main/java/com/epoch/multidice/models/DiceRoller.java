package com.epoch.multidice.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A DiceRoller is a builder-type object that accepts an input string from the front-end form, parses it and generates the appropriate Handful objects, then coordinates the actions of the Handfuls in building a RollEvent object to be persisted in the database, containing all the relevant information and results. 
 * @author Epoch
 * @version 0.3
 * @since 0.1
 *
 */
public class DiceRoller {
	
	// attributes

	private RollEvent event;
	private ArrayList<Handful> handfuls;
	
	// constructors
	
	public DiceRoller() {}
	
	public DiceRoller(RollEvent event) {
		this.event = event;
	}
	
	// get & set
	
	public RollEvent getEvent() {
		return event;
	}

	public void setEvent(RollEvent event) {
		this.event = event;
	}

	public ArrayList<Handful> getHandfuls() {
		return handfuls;
	}

	public void setHandfuls(ArrayList<Handful> handfuls) {
		this.handfuls = handfuls;
	}
	
	// other methods

	/**
	 * Pseudo-controller that moderates input string parsing, coordinates the Handful objects, and builds the final RollEvent.
	 * @return A RollEvent populated with all info and ready to be stored in the database.
	 */
	public RollEvent roll() {
		this.handfuls = parseToHandfuls(event.getInputString());
		ArrayList<ArrayList<Integer>> rawResults = new ArrayList<ArrayList<Integer>>();
		// ArrayList<String> finalResults = new ArrayList<String>();
		// iterate through handfuls, calling each one's rollDice()
		for(Handful h : handfuls) {
			h.rollDice();
		}
		
		// create appropriate ArrayList<String>s for event's rawResults
		// at same time, sum results for finalResults - debug only, needs to be changed to match modifiers
		int reported = 0;
		for(Handful h : handfuls) {
			rawResults.add(h.getRawResults());
			reported += h.getReported();
		}
		
		// build ArrayList to define event's rawResults
		ArrayList<String> rawToSet = new ArrayList<String>();
		for(ArrayList<Integer> l : rawResults) {
			rawToSet.add(l.toString());
		}
		event.setRawResults(rawToSet);
		
		// set finalResults to an ArrayList containing only the reported sum
		// this is mostly a placeholder for later, more complicated reporting, not just sum-all-dice
		ArrayList<String> finalToSet = new ArrayList<String>();
		finalToSet.add(Integer.toString(reported));
		event.setFinalResults(finalToSet);
		return event;
	}
	
	/**
	 * Breaks a user-defined input string into pieces and generates a list of Handful objects to be rolled.
	 * @param input A raw input string of one or more dice with modifiers.
	 * @return An ArrayList of Handful objects.
	 */
	public ArrayList<Handful> parseToHandfuls(String input){
		String[] splitString = input.split("\\+");
		ArrayList<Handful> output = new ArrayList<Handful>();
		for(String s : splitString) {
			Handful h = new Handful(s.replaceAll("\\s+", ""));
			output.add(h);
		}
		return output;
	}

}
