package com.epoch.multidice.models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 
 * A Handful is a set of one or more dice "objects" that are meant to be rolled at once and have the same modifiers applied to all members.
 * Handfuls are not intended to be saved or persisted in the database.
 * @author Epoch
 * @version 0.2
 * @since 0.1
 *
 */

public class Handful {
	
	private String input;
	private ArrayList<String> commands;
	private ArrayList<Integer> rawResults;
	 private ArrayList<Integer> finalResults;
	private HashMap<String, String> mods;
		// parse through keylist and switch-case each one
	private SecureRandom random = new SecureRandom();
	
	// constructors
	
	public Handful(String input) {
		this.input = input;
		this.commands = null;
		this.rawResults = null;
		this.finalResults = null;
		this.mods = new HashMap<String, String>();
	}
	
	// get & set
	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public ArrayList<Integer> getRawResults() {
		return rawResults;
	}

	public HashMap<String, String> getMods() {
		return mods;
	}
	
	public ArrayList<Integer> getFinalResults() {
		return finalResults;
	}
	
	// other methods
	
	
	/**
	 * Pseudo-controller that moderates the process of parsing input, rolling dice, and applying modifiers.
	 */
	public void rollDice(){
		// may need to try-catch if input string is malformed
		this.commands = parseToCommands(input);
		this.rawResults = rollem(commands.remove(0));
		parseModifiers(commands);
		// this.finalResults = doModifiers();
		this.finalResults = rawResults;  // debug/dev purposes only, remove once doModifiers() works
		return;
	}
	

	/**
	 * Breaks a single input string into an iterable series of commands.
	 * @param input A string consisting of a dice definition and a series of modifiers.
	 * @return An ArrayList<String> consisting of a dice definition at pos 0, then (optionally) a series of modifiers.
	 */
	private ArrayList<String> parseToCommands(String input){
		String[] splitString = input.split("\\.");
		ArrayList<String> output = new ArrayList<String>();
		Collections.addAll(output, splitString);
//		System.out.println("Result of parseToCommands:");
//		System.out.println(output);
		return output;
	}
	
	/**
	 * Takes a dice definition and generates an ArrayList of raw, unmodified results.
	 * @param dice A dice definition in the format "NdS" where n = quantity and s = either # of sides, or a custom die identifier.
	 * @return An ArrayList<Integer> of raw results from the rolled dice.
	 */
	private ArrayList<Integer> rollem(String dice) {
		String[] numbers = dice.split("d");
//		System.out.println("Executing rollem:");
//		System.out.println("heard "+dice);
//		System.out.println("quantity "+numbers[0]+", type "+numbers[1]);
		int quantity = Integer.parseInt(numbers[0]);
		int max;
		int min;
		try {
			max = Integer.parseInt(numbers[1]);
			min = 1;
		} catch (NumberFormatException e) {
			if(numbers[1].toLowerCase().equals("f")) {
				max = 1;
				min = -1;
			} else {
				// if-else other custom die types:  Genesys, custom-faces, ...?
				throw new NumberFormatException("Unable to parse die type");
			}
		}
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i=0; i<quantity; i++) {
			results.add(random.nextInt(max-min+1)+min);
		}
//		System.out.println(results);
		return results;
	}
	
	
	/**
	 * Takes the list of modifiers and generates a HashMap "mods" of necessary modifiers with their parameters, ready to be executed.
	 * @param modStrings An ArrayList<String> of modifier definitions.
	 */
	private void parseModifiers(ArrayList<String> modStrings) {
//		System.out.println("Results of parseModifiers:");
		for(String m : modStrings) {
			String[] thisMod = m.split("[\\(\\)]");
			mods.put(thisMod[0], thisMod[1]);
//			for(String s : thisMod) {
//				System.out.print(s+", ");
//			}
		}
	}
	
	
	/**
	 * Given rawResults and a HashMap of mods with parameters, applies modifiers and generates finalResults.
	 * @return An ArrayList<Integer> of results after applying modifiers.
	 */
	private ArrayList<Integer> doModifiers() {
		ArrayList<Integer> output = new ArrayList<Integer>();
		// work through HashMap of mods and operate on rawResults
		return output;
	}
	


}
