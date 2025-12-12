package task4;

import java.util.function.BiPredicate;

public class StringUtils {
    public static String getBetterString(String s1, String s2, BiPredicate<String, String> betterPredicate) {
        if (betterPredicate.test(s1, s2)) {
            return s1;
        } else {
            return s2;
        }
    }
}
