package com.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class EquationTask {

    @FunctionalInterface
    public interface EquationSolver <Double> {
        void compute(Double a, Double b, Double c);
    }
    

	public static void runTests(){
        System.out.println("\n--- Testing EquationTask ---");

        EquationSolver<Double> solver = (a, b, c) -> {
            double discriminant = b * b - 4 * a * c;

            if(discriminant > 0) {

                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                System.out.format("root1 = %.2f and root2 = %.2f\n", root1, root2);

            } else if (discriminant == 0) {

                double root = -b / (2 * a);
                System.out.format("root1 = root2 = %.2f;\n", root);

            } else {

                System.out.println("No real roots (discriminant < 0)\n");  
            }

        };

        processUserInput(solver);

	}

    public static void processUserInput(EquationSolver<Double> solver){
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter a: ");
            double a = scanner.nextDouble();

            System.out.print("Enter b: ");
            double b = scanner.nextDouble();

            System.out.print("Enter c: ");
            double c = scanner.nextDouble();

            if(Math.abs(a) < 1e-9){
                System.out.println("\nParameter a cannot be ZERO");
                return;
            }

            solver.compute(a, b, c);

        } catch (InputMismatchException e) {
            System.out.println("\nPlease enter numeric values only");

        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }
    }
}