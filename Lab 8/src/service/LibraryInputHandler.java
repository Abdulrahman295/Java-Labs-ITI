package service;

import enums.LibraryMenuOption;

import java.util.Scanner;

public class LibraryInputHandler implements AutoCloseable {
    private final Scanner scanner;

    public LibraryInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public Integer getPositiveIntegerInput(String prompt) {
        return getInput(prompt, input -> {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                throw new IllegalArgumentException("Number must be positive.");
            }
            return value;
        });
    }

    public Integer getIntegerInputOrDefault(String label, Integer currentValue) {
        String prompt = String.format("%s (current: %s): ", label, currentValue);
        return getInput(prompt, input -> {
            if (input.isEmpty()) {
                return currentValue;
            }

            int val = Integer.parseInt(input);
            if (val < 0) {
                throw new IllegalArgumentException("Value must be positive.");
            }
            return val;
        });
    }

    public String getStringInput(String prompt) {
        return getInput(prompt, input -> {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
            return input.trim();
        });
    }

    public String getStringInputOrDefault(String label, String currentValue) {
        String prompt = String.format("%s (current: %s): ", label, currentValue);

        return getInput(prompt, input -> {
            if (input.isEmpty()) {
                return currentValue;
            }
            return input;
        });
    }

    public LibraryMenuOption getMenuOption() {
        return getInput("Enter choice: ", input -> {
            int choiceId = Integer.parseInt(input);
            return LibraryMenuOption.fromId(choiceId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid option ID."));
        });
    }

    @Override
    public void close() {
        scanner.close();
    }

    private <T> T getInput(String prompt, InputParser<T> parser) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return parser.apply(input);
            } catch (Exception e) {
                System.out.println("Invalid Input: " + e.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface InputParser<T> {
        T apply(String input) throws Exception;
    }
}
