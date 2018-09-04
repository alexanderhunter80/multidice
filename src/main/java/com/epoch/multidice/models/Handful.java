package com.epoch.multidice.models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

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
	private Integer quantity;
	private Integer lo;
	private Integer hi;
	private ArrayList<String> commands;
	private ArrayList<Integer> rawResults;
	private ArrayList<Integer> finalResults;
	private Integer reported;
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
	
	public int getReported() {
		return reported;
	}
	
	// other methods
	
	
	/**
	 * Pseudo-controller that moderates the process of parsing input, rolling dice, and applying modifiers.
	 */
	public void rollDice(){
		System.out.println("Handful is parsing input: "+input);
		try {
			
			int k = Integer.parseInt(input.replaceAll("[\\(\\)]", ""));
			System.out.println("Input is a scalar value, bypassing all other logic");
			reported = k;
			rawResults = new ArrayList<Integer>();
			rawResults.add(k);
			finalResults = new ArrayList<Integer>(rawResults);
			return;
		} catch (NumberFormatException e) {
			System.out.println("Input is not a scalar value, continuing");
		}
		// may need to try-catch if input string is malformed
		this.commands = parseToCommands(input);
		this.rawResults = rollem(commands.remove(0));
		parseModifiers(commands);
		this.finalResults = doModifiers();
//		this.finalResults = rawResults;  // debug/dev purposes only, remove once doModifiers() works
		this.reported = 0;
		for(Integer i : finalResults) {
			reported += i;
		}
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
		quantity = Integer.parseInt(numbers[0]);
		try {
			hi = Integer.parseInt(numbers[1]);
			lo = 1;
		} catch (NumberFormatException e) {
			if(numbers[1].toLowerCase().equals("f")) {
				hi = 1;
				lo = -1;
			} else {
				// if-else other custom die types:  Genesys, custom-faces, ...?
				throw new NumberFormatException("Unable to parse die type");
			}
		}
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i=0; i<quantity; i++) {
			results.add(rollOne(lo,hi));
		}
//		System.out.println(results);
		return results;
	}
	
	/**
	 * Rolls a single plain sequential die.
	 * @param lo
	 * @param hi
	 * @return An Integer that the die rolled.
	 */
	
	private Integer rollOne(Integer lo, Integer hi) {
		return random.nextInt(hi-lo+1)+lo;
	}
	
	
	/**
	 * Takes the list of modifiers and generates a HashMap "mods" of necessary modifiers with their parameters, ready to be executed.
	 * @param modStrings An ArrayList<String> of modifier definitions.
	 */
	private void parseModifiers(ArrayList<String> modStrings) {
		System.out.println("Results of parseModifiers:");
		for(String m : modStrings) {
			String[] thisMod = m.split("[\\(\\)]");
			mods.put(thisMod[0], thisMod[1]);
			for(String s : thisMod) {
				System.out.print(s+", ");
			}
		}
		System.out.println();
	}
	
	
	/**
	 * Given rawResults and a HashMap of mods with parameters, applies modifiers and generates finalResults.
	 * @return An ArrayList<Integer> of results after applying modifiers.
	 */
	private ArrayList<Integer> doModifiers() {
		Integer gt, gte, lt, lte, count;
		ArrayList<Integer> output = new ArrayList<Integer>(rawResults);
		System.out.println("doModifiers working with "+output);
		
		// work through HashMap of mods and operate on rawResults
		
		// add/remove dice:  explode, rexplode, explodeAt, rexplodeAt
		
		if(mods.containsKey("explode")) {
			System.out.println("processing explode()");
			gte = Integer.valueOf(mods.get("explode"));
			count = 0;
			// count dice above threshold
			for(Integer i : output) {
				if(i >= gte) {
					count++;
				}
			}
			// roll added dice
			for(int i=0; i<count; i++) {
				output.add(rollOne(lo,hi));
			}
		}
		if(mods.containsKey("explodeRange")) {
			// explodeAt
			notImplemented("explodeRange");
		}
		if(mods.containsKey("recursiveExplode") || mods.containsKey("rexplode")) {
			gte = Integer.valueOf(mods.getOrDefault("recursiveExplode", mods.get("rexplode")));
			count = 0;
			// count dice above threshold
			for(Integer i : output) {
				if(i >= gte) {
					count++;
				}
			}
			// roll added dice, exploding again if new result is above threshold
			for(int i=0; i<count; i++) {
				Integer a = rollOne(lo,hi);
				output.add(a);
				if(a >= gte) {
					i--;
				}
			}
		}
		if(mods.containsKey("recursiveExplodeRange") || mods.containsKey("rexplodeRange")) {
			// recursiveExplodeAt
			notImplemented("recursiveExplodeRange");
		}
		
		// alter dice:  maximize/max, minimize/min
		
		if(mods.containsKey("maximize") || mods.containsKey("max")) {
			// maximize
			notImplemented("maximize");
		}
		if(mods.containsKey("minimize") || mods.containsKey("min")) {
			// minimize
			notImplemented("minimize");
		}
		
		// keep&discard dice:  keepHighest, keepLowest, keepAbove, keepBelow, discardHighest, discardLowest
		
		// dice math:  sum, count (3 versions), hits, glitches, and eventually sets
		
		
		return output;
	}
	
	private void notImplemented(String s) {
		System.out.println("Handful heard "+s+", but that function is not yet implemented.");
	}

}
