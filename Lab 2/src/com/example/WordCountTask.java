package com.example;  

public class WordCountTask {
	public static void runTests(){
		String sentence = "ITI develops people and ITI house of developers and ITI for people";
        String target = "ITI";

        System.out.println("\n--- Testing WordCountTask ---");

        System.out.println("Sentence:" + sentence);
        System.out.println("target:" + target);

        System.out.print("Method 1 (indexOf): ");
        testCountUsingIndexOf(sentence, target);

        System.out.print("Method 2 (split):   ");
        testCountUsingSplit(sentence, target);

	}

	public static void testCountUsingIndexOf(String text, String target){
		int index = 0;
		int count = 0;

		index = text.indexOf(target, index);

		while(index != -1){
			count++;
			index = text.indexOf(target, index + target.length());
		}

		System.out.println("Count is: " + count);
	}

	public static void testCountUsingSplit(String text, String target){
		 String[] words = text.split(" ");
		 int count = 0;

		 for(String word : words){
		 	if(word.equals(target)){
		 		count++;
		 	}
		 }


		 System.out.println("Count is: " + count);
	}
}