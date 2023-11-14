package christmas.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public record OrderDTO(Map<String, Integer> orders) {
    private static final String ERROR_FORMAT = "[ERROR] %s";
    private static final String CANNOT_ORDER_ONLY_JUICE = "음료만 주문할 수 없습니다.";
    private static final String INVALID_MAX_COUNT_FORMAT = "메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다.";
    private static final String INVALID_MIN_COUNT_FORMAT = "메뉴는 최소 %d개 이상부터 주문할 수 있습니다.";
    private static final int MAX_MENU_COUNT = 20;
    private static final int MIN_MENU_COUNT = 1;
    private static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public OrderDTO {
        validateMenuNames();
        validateOnlyJuice();
        int totalCount = getTotalCount();
        validateMaxMenuCount(totalCount);
        validateMinMenuCount(totalCount);
        validateDuplicateOrder();
    }

    private void validateMenuNames() {
        if (!isMenuNamesPresent()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER));
        }
    }

    private boolean isMenuNamesPresent() {
        return orders.keySet().stream()
                .anyMatch(menuName -> Menu.findMenuByName(menuName.toUpperCase()).isPresent());
    }

    private void validateOnlyJuice() {
        if (orders.keySet().stream()
                .map(Menu::findMenuByName)
                .allMatch(menu -> menu.map(value -> "음료".equals(value.getCategory())).orElse(false))) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, CANNOT_ORDER_ONLY_JUICE));
        }
    }

    private void validateMaxMenuCount(final int totalCount) {
        if (totalCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ERROR_FORMAT, String.format(INVALID_MAX_COUNT_FORMAT, MAX_MENU_COUNT)));
        }
    }

    private void validateMinMenuCount(final int totalCount) {
        if (totalCount < MIN_MENU_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ERROR_FORMAT, String.format(INVALID_MIN_COUNT_FORMAT, MIN_MENU_COUNT)));
        }
    }

    private int getTotalCount() {
        return orders.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateDuplicateOrder() {
        if (hasDuplicate()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_ORDER));
        }
    }

    private boolean hasDuplicate() {
        Set<String> uniqueStrings = new HashSet<>();

        return orders.keySet().stream()
                .anyMatch(orderString -> !uniqueStrings.add(orderString));
    }
}
