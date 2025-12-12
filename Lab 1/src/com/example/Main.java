package com.example;

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void testMinAndMax(){
        int SIZE = 1000;
        int[] randomArray = new int[SIZE];

        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            randomArray[i] = rand.nextInt(1000); 
        }

        int min =  randomArray[0], max = randomArray[0];

        System.out.println("Searching for min and max using Linear Search...");


        for(int i = 1; i < SIZE; i++){
            if(randomArray[i] > max) max = randomArray[i];
            
            if(randomArray[i] < min) min = randomArray[i];
        }

        System.out.println("Min: " + min + ", Max: " + max);
        
    }

    public static void testBinarySearch(){
        int SIZE = 1000;
        int[] randomArray = new int[SIZE];

        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            randomArray[i] = rand.nextInt(1000); 
        }

        int randomIndex = rand.nextInt(SIZE);
        int target = randomArray[randomIndex];

        System.out.println("Searching for " + target + " using Binary Search...");

        Arrays.sort(randomArray);

        int l = 0, r = SIZE - 1;

         while (l <= r){            
            int m = (l + r) / 2;

            if (randomArray[m] == target) {
                System.out.println("Found.");
                return;
            } else if (randomArray[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        System.out.println("Not Found.");
    }

    public static void testCommandInputs(String[] args) {
        System.out.println("Processing command line arguments...");

        if (args.length == 0) {
            System.out.println(">>>No arguments provided.");
            return;
        }

        if (args.length == 1) {
            System.out.println("Single input received: " + args[0]);
            return;
        }

        try {

            int count = Integer.parseInt(args[0]); 
            String text = args[1];

            if(count <= 0){
                System.out.println("Count must be positive number");
                return;
            }

            System.out.println("Printing '" + text + "' " + count + " times:");
            for (int i = 0; i < count; i++) {
                System.out.println(text);
            }

        } catch (Exception e){
            System.out.println(e);
        }

    }


    public static void main(String[] args) {
        System.out.println("==== Part 1 =====");
        long start = System.nanoTime();
        testMinAndMax();
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms");

        System.out.println("==== Part 2 =====");
        start = System.nanoTime();
        testBinarySearch();
        end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms");


        System.out.println("==== Part 3 =====");
        testCommandInputs(args);
    }
}