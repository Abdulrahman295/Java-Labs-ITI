package task5;

public class TestAlphaString {
    public static void runTests() {
        String testStr1 = "HelloWorld";
        String testStr2 = "Hello123";

        isAlphaString(testStr1);
        isAlphaString(testStr2);
    }

    public static void isAlphaString(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isLetter(c)) {
                System.out.println("String contains non-alphabetic characters");
                return;
            }
        }

        System.out.println("String is alphabetic");
    }
}
