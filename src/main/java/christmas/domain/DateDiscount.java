package christmas.domain;

public class DateDiscount {
    private static final int START_DATE = 1;
    private static final int END_DATE = 25;
    private static final int MIN_DISCOUNT = 1_000;
    private static final int DISCOUNT_PER_DATE = 100;

    private DateDiscount() {
    }

    public static int getDateDiscount(final int date) {
        if (isDiscountDate(date)) {
            return MIN_DISCOUNT + (date - 1) * DISCOUNT_PER_DATE;
        }
        return 0;
    }

    private static boolean isDiscountDate(final int date) {
        return date >= START_DATE && date <= END_DATE;
    }
}
