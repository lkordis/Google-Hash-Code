package com.google.hashcode;

import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		Pizza pizza = new Pizza(Paths.get(".\\big (1).in"));
		pizza.generateCombinations();
		pizza.findSlices();
		
		pizza.writeOutput(".\\big.out");
	}
	
}
