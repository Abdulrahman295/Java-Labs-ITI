import task1.TestValidator;
import task2.TestShape;
import task3.TestComplex;
import task4.TestBetterString;
import task5.TestAlphaString;

public class Main {
    public static void main(String[] args) {
        TestValidator.runTests();
        TestShape.runTests();
        TestComplex.runTests();
        TestBetterString.runTests();
        TestAlphaString.runTests();
    }
}