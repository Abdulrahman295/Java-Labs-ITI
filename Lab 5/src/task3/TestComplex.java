package task3;

public class TestComplex {
    public static void runTests() {
        System.out.println("=== Testing Doubles ===");
        GenericComplex<Double> d1 = new GenericComplex<>(3.5, 4.5);
        GenericComplex<Double> d2 = new GenericComplex<>(1.5, 2.5);
        GenericComplex<Double> dResult = d1.addAsGeneric(d2);
        System.out.println("Double Result real: " + dResult.getReal() + ", imaginary: " + dResult.getImaginary());
        System.out.println("Is Result Double? " + (dResult.getReal() instanceof Double));

        System.out.println("\n=== 2. Testing Integers ===");
        GenericComplex<Integer> i1 = new GenericComplex<>(10, 20);
        GenericComplex<Integer> i2 = new GenericComplex<>(5, 5);
        GenericComplex<Integer> iResult = i1.addAsGeneric(i2);
        System.out.println("Integer Result real: " + iResult.getReal() + ", imaginary: " + iResult.getImaginary());
        System.out.println("Is Result Integer? " + (iResult.getReal() instanceof Integer));
    }
}
