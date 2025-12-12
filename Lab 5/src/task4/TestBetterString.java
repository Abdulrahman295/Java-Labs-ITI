package task4;

public class TestBetterString {
    public static void runTests(){
        String s1 = "lemon";
        String s2 = "ananana";

        String longer = StringUtils.getBetterString(s1, s2, (str1, str2) -> str1.length() > str2.length());
        System.out.println("Longer string: " + longer);

        String first = StringUtils.getBetterString(s1, s2, (str1, str2) -> true);
        System.out.println("First string: " + first);
    }
}
