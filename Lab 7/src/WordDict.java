import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class WordDict {
    private final Map<Character, Set<String>> dict;

    public WordDict() {
        this.dict = new TreeMap<>();

        IntStream.rangeClosed('a', 'z')
                .mapToObj(c -> (char) c)
                .forEach(letter -> dict.put(letter, new java.util.TreeSet<>()));
    }

    public void putWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }

        char firstChar = Character.toLowerCase(word.charAt(0));

        Set<String> wordsSet = dict.get(firstChar);
        if (wordsSet != null) {
            wordsSet.add(word);
        }
    }

    public void printWordsForLetter(char letter) {
        char lowerLetter = Character.toLowerCase(letter);
        Set<String> wordsSet = dict.get(lowerLetter);
        if (wordsSet != null && !wordsSet.isEmpty()) {
            wordsSet.forEach(System.out::println);
        } else {
            System.out.println("No words found for letter: " + letter);
        }
    }

    public void printDictionary() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            System.out.println("Words starting with '" + letter + "':");
            printWordsForLetter(letter);
            System.out.println("----------------------" );
        }
    }
}
