package com.epoch.multidice.tools;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Handful {
	
	private String input;
	private ArrayList<String> commands;
	private ArrayList<Integer> rawResults;
	private ArrayList<Integer> output;
	private HashMap<String, String> mods;
		// parse through keylist and switch-case each one
	SecureRandom random = new SecureRandom();
	
	// constructors
	
	public Handful(String input) {
		this.input = input;
		this.commands = parseToCommands(input);
		this.rawResults = null;
		this.output = null;
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

	public ArrayList<Integer> getOutput() {
		return output;
	}

	public HashMap<String, String> getMods() {
		return mods;
	}
	
	// other methods
	
	public ArrayList<Integer> rollDice(){
		this.rawResults = rollem(commands.remove(0));
		parseModifiers(commands);
		// run modifiers
		return null;
	}

	private ArrayList<String> parseToCommands(String single){
		System.out.println("Result of parseToCommands:");
		String[] splitString = single.split("\\.");
		ArrayList<String> output = new ArrayList<String>();
		Collections.addAll(output, splitString);
		System.out.println(output);
		return output;
	}
	
	private void parseModifiers(ArrayList<String> modStrings) {
		System.out.println("Results of parseModifiers:");
		for(String m : modStrings) {
			String[] thisMod = m.split("[\\(\\)]");
			for(String s : thisMod) {
				System.out.print(s+", ");
			}
			System.out.println();
			mods.put(thisMod[0], thisMod[1]);
		}
	}
	
	private ArrayList<Integer> rollem(String die) {
		System.out.println("Executing rollem:");
		System.out.println("heard "+die);
		String[] numbers = die.split("d");
		System.out.println("quantity "+numbers[0]+", type "+numbers[1]);
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
				throw new NumberFormatException("Unable to parse die type");
			}
		}
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i=0; i<quantity; i++) {
			results.add(random.nextInt(max-min+1)+min);
		}
		System.out.println(results);
		return results;
	}

}
