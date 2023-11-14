package christmas.domain;

public class PresentEvent {
    private static final int MIN_ORDER_PRICE_FOR_PRESENT = 120_000;
    private static final String PRESENT = "샴페인";

    private PresentEvent() {
    }

    public static String getPresent(final int totalOrderPrice) {
        if (totalOrderPrice >= MIN_ORDER_PRICE_FOR_PRESENT) {
            return PRESENT;
        }
        return "없음";
    }
}
