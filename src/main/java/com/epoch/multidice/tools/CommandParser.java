package com.epoch.multidice.tools;

import java.util.ArrayList;

public class CommandParser {
	
	// constructors
	
	public CommandParser() {}
	
	// get & set
	
	// other methods
	
	public static ArrayList<Handful> parseToHandfuls(String input){
		String[] splitString = input.split("\\+");
		ArrayList<Handful> output = new ArrayList<Handful>();
		for(String s : splitString) {
			Handful h = new Handful(s.trim());
			output.add(h);
		}
		return output;
	}
	
	
}
