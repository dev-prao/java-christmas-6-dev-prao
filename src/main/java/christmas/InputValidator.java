package christmas;

import christmas.domain.Menu;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InputValidator {
    private static final String ERROR_FORMAT = "[ERROR] %s";
    private static final String EMPTY = "값을 입력하지 않으셨습니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_MENU = "유효하지 않은 메뉴 이름이 포함되어 있습니다. 다시 입력해 주세요.";
    private static final String CANNOT_ORDER_ONLY_JUICE = "음료만 주문할 수 없습니다.";
    private static final String INVALID_STRING_COUNT_FORMAT = "메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다.";
    private static final int MAX_MENU_COUNT = 20;
    private static final String INVALID_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private InputValidator() {
    }

    public static void validateEmpty(final String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, EMPTY));
        }
    }

    public static void validateMenuNames(Map<String, Integer> orders) {
        if (!isAllMenuNamesValid(orders)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER_MENU));
        }
    }

    private static boolean isAllMenuNamesValid(Map<String, Integer> orders) {
        return orders.keySet().stream()
                .allMatch(menuName -> Menu.findMenuByName(menuName.toUpperCase()) != null);
    }

    public static void validateOnlyJuice(final Set<String> categories) {
        if (categories.size() == 1 && categories.contains("음료")) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, CANNOT_ORDER_ONLY_JUICE));
        }
    }

    public static void validateMaxMenuCount(final int count) {
        if (count > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ERROR_FORMAT, String.format(INVALID_STRING_COUNT_FORMAT, MAX_MENU_COUNT)));
        }
    }

    private static void validateOrderCount(Map<String, Integer> orders) {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER));
        }
    }

    public static void validateDate(final int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_VISIT_DATE));
        }
    }

    public static void validateOrder(Map<String, Integer> orders) {
        validateMenuName(orders);
        validateOrderCount(orders);
        validateDuplicateOrder(orders);
    }

    private static void validateMenuName(Map<String, Integer> orders) {
        if (!isMenuNamesPresent(orders)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER));
        }
    }

    private static boolean isMenuNamesPresent(Map<String, Integer> orders) {
        return orders.keySet().stream()
                .anyMatch(menuName -> Menu.findMenuByName(menuName) != null);
    }

    private static void validateDuplicateOrder(Map<String, Integer> orders) {
        if (hasDuplicate(orders)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER));
        }
    }

    private static boolean hasDuplicate(Map<String, Integer> orders) {
        Set<String> uniqueStrings = new HashSet<>();

        return orders.keySet().stream()
                .anyMatch(orderString -> !uniqueStrings.add(orderString));
    }
}
