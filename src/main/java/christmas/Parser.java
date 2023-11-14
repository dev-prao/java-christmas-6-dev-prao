package christmas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private static final Pattern pattern = Pattern.compile("([가-힣a-zA-Z0-9]+)-(\\d+)");
    private static final String ERROR_FORMAT = "[ERROR] %s";
    private static final String EMPTY = "값이 비어있습니다. 다시 입력해주세요.";
    private static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private Parser() {
    }

    public static int parseDate(final String input) {
        validateEmpty(input);
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> parseOrder(final String input) {
        validateEmpty(input);

        Map<String, Integer> orders = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .collect(Collectors.toMap(
                        match -> match.group(1),
                        match -> Integer.parseInt(match.group(2)),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER));
                        }
                ));

        return Collections.unmodifiableMap(orders);
    }

    public static void validateEmpty(final String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, EMPTY));
        }
    }
}
