package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventBadgeTest {

    @DisplayName("이벤트 배지가 정상적으로 생성되는지 테스트")
    @ParameterizedTest
    @CsvSource({
            "25_000, 산타",
            "12_000, 트리",
            "6_000, 별",
            "4_000, 없음"
    })
    void createEventBadge(int totalDiscount, String expectedBadge) {
        // When
        EventBadge badge = EventBadge.create(totalDiscount);

        // Then
        assertEquals(EventBadge.valueOf(expectedBadge), badge);
    }
}
