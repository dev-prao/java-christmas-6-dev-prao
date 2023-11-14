package christmas.domain;

import java.util.Arrays;

public enum EventBadge {
    산타(20_000, "산타"),
    트리(10_000, "트리"),
    별(5_000, "별"),
    없음(0, "없음");

    private final int threshold;
    private final String badge;

    EventBadge(int threshold, String badge) {
        this.threshold = threshold;
        this.badge = badge;
    }

    public static EventBadge create(int totalDiscount) {
        return Arrays.stream(values())
                .filter(badge -> totalDiscount >= badge.threshold)
                .findFirst()
                .orElse(없음);
    }

    public String getBadge() {
        return badge;
    }
}
