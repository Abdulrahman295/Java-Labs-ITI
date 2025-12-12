package com.example;

import java.util.StringTokenizer;

public class TokenizerTask {
	public static void runTests(){
		String sentence = "ITI develops people and ITI house of developers and ITI for people";
        String Delimiter = "and";

        System.out.println("\n--- Testing TokenizerTask ---");

        System.out.println("Sentence:" + sentence);
        System.out.println("target:" + Delimiter);
        System.out.println();

       testTokenizeUsingWordDelimiter(sentence, Delimiter);
	}

	public static void testTokenizeUsingWordDelimiter(String text, String Delimiter){
		StringTokenizer st = new StringTokenizer(text);

		StringBuilder currentSegment = new StringBuilder();

        while (st.hasMoreTokens()) {

        	String token = st.nextToken();

        	if(token.equals(Delimiter)) {
        		System.out.println(currentSegment.toString().trim());
        		currentSegment.setLength(0);
        	} else {
        		currentSegment.append(token).append(" ");
        	}

        }

        if (currentSegment.length() > 0) {
        	System.out.println(currentSegment.toString().trim());
        }
	}

}