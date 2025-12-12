import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RegistrationInputHandler implements AutoCloseable {
    private final Scanner scanner;

    public RegistrationInputHandler(){
        this.scanner = new Scanner(System.in);
    }

    public Integer getIntegerInput(String prompt) {
        return getInput(prompt, Integer::parseInt);
    }

    public Double getDoubleInput(String prompt) {
        return getInput(prompt, Double::parseDouble);
    }

    public List<Integer> getIntegerListInput(String prompt) {
        return getInput(prompt, input -> {
            StringTokenizer tokenizer = new StringTokenizer(input, ",");
            List<Integer> integerList = new ArrayList<>();

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken().trim();
                integerList.add(Integer.parseInt(token));
            }
            return integerList;
        });
    }
    public String getStringInput(String prompt) {
        return getInput(prompt, input -> input);
    }

    public MenuOption getMenuOption(){
        return getInput("Enter choice: ", input -> {
            int choiceId = Integer.parseInt(input);
            return MenuOption.fromId(choiceId);
        });
    }

    @Override
    public void close() {
        scanner.close();
    }

    private <T> T getInput(String prompt, InputParser<T> parser) {
        System.out.print(prompt);

        try {
            String input = scanner.nextLine().trim();
            return parser.apply(input);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @FunctionalInterface
    private interface InputParser<T> {
        T apply(String input) throws Exception;
    }
}
