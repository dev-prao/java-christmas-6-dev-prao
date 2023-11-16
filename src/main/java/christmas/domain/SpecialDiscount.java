package christmas.domain;

import java.util.List;

public class SpecialDiscount {
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final List<Integer> STARS = List.of(3, 10, 17, 24, 25, 31);

    private SpecialDiscount() {
    }

    public static int getSpecialDiscount(final int date) {
        if (STARS.contains(date)) {
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }
}
