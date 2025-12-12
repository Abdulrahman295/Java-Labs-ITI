package task1;

public class CustomValidator {
    public static void validatePositive(int number) {
        if (number <= 0) {
            throw new CustomException("Number must be positive");
        }
    }

    public static void validateNonEmpty(String str) {
        if (str == null || str.isEmpty()) {
            throw new CustomException("String must be non-empty");
        }
    }

    public static void validateInRange(int number, int min, int max) {
        if (number < min || number > max) {
            throw new CustomException("Number is out of range");
        }
    }
}
