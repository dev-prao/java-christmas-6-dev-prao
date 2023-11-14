package christmas;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private static final Pattern pattern = Pattern.compile("([가-힣a-zA-Z0-9]+)-(\\d+)");

    private Parser() {
    }

    public static int parseDate(final String input) {
        InputValidator.validateEmpty(input);
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> parseOrder(final String input) {
        InputValidator.validateEmpty(input);

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .collect(Collectors.toMap(
                        match -> match.group(1),
                        match -> Integer.parseInt(match.group(2))
                ));
    }


}
