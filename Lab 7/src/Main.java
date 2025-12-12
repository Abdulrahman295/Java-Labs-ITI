public class Main {
    public static void main(String[] args) {
        WordDict wordDict = new WordDict();

        wordDict.putWord("apple");
        wordDict.putWord("banana");
        wordDict.putWord("apricot");
        wordDict.putWord("blueberry");
        wordDict.putWord("cherry");

        wordDict.printDictionary();
    }
}