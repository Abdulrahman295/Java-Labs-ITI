package task1;

public class TestValidator {
    public static void runTests() {
        testValidatePositive();
        testValidateNonEmpty();
        testValidateInRange();
    }

    public static void testValidatePositive(){
        try {
            int testNumber = -5;
            CustomValidator.validatePositive(testNumber);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("testValidatePositive completed");
        }
    }

    public static void testValidateNonEmpty(){
        try {
            String testString = "";
            CustomValidator.validateNonEmpty(testString);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("testValidateNonEmpty completed");
        }
    }

    public static void testValidateInRange(){
        try {
            int testNumber = 15;
            CustomValidator.validateInRange(testNumber, 1, 10);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("testValidateInRange completed");
        }
    }
}
