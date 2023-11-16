package christmas.domain;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

public class DayDiscount {
    private static final int FRIDAY = 1;
    private static final int SATURDAY = 2;
    private static final int DISCOUNT_PER_DAY = 2_023;

    private DayDiscount() {
    }

    public static int getDayDiscount(final int date, Map<String, Integer> orders) {
        int rest = getRest(date);

        if (isWeekend(rest)) {
            return weekendDiscount(orders);
        }
        return weekdayDiscount(orders);
    }

    private static int weekendDiscount(Map<String, Integer> orders) {
        return orders.entrySet().stream()
                .filter(entry -> {
                    Optional<Menu> optionalMenu = Menu.findMenuByName(entry.getKey());
                    return optionalMenu.isPresent() && Objects.equals(optionalMenu.get().getCategory(), "메인");
                })
                .mapToInt(Entry::getValue)
                .sum() * DISCOUNT_PER_DAY;
    }

    private static int weekdayDiscount(Map<String, Integer> orders) {
        return orders.entrySet().stream()
                .filter(entry -> {
                    Optional<Menu> optionalMenu = Menu.findMenuByName(entry.getKey());
                    return optionalMenu.isPresent() && Objects.equals(optionalMenu.get().getCategory(), "디저트");
                })
                .mapToInt(Entry::getValue)
                .sum() * DISCOUNT_PER_DAY;
    }

    private static boolean isWeekend(final int rest) {
        return rest == FRIDAY || rest == SATURDAY;
    }

    private static int getRest(final int date) {
        return date % 7;
    }
}
