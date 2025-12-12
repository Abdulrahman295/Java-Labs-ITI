package com.example; 

import java.util.function.Function;


public class TemperatureTask {
	public static void runTests(){
		System.out.println("\n--- Testing TemperatureTask ---");

    Function<Double, Double> convert = celsius -> (celsius * 1.8) + 32;

    double celsius = 10.0;

    double fahrenheit = convert.apply(celsius);

    System.out.println("value of temperature in fahrenheit: " + fahrenheit);

	}

}