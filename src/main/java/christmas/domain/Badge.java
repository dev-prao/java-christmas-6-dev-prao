package christmas.domain;

public class Badge {
    private static final int FIRST_LEVEL = 5_000;
    private static final int SECOND_LEVEL = 10_000;
    private static final int THIRD_LEVEL = 20_000;

    private Badge() {
    }

    public static String getBadge(final int totalDiscount) {
        if (totalDiscount >= THIRD_LEVEL) {
            return "산타";
        }
        if(totalDiscount >= SECOND_LEVEL) {
            return "트리";
        }
        if(totalDiscount >= FIRST_LEVEL) {
            return "별";
        }
        return "없음";
    }
}
