package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PresentEventTest {

    @DisplayName("주문 금액에 따라 올바른 선물이 생성되는지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 119_999, 120_000, 200_000, 300_000})
    void getPresent(int totalOrderPrice) {
        // When
        String present = PresentEvent.getPresent(totalOrderPrice);

        // Then
        assertEquals(getExpectedPresent(totalOrderPrice), present);
    }

    private String getExpectedPresent(int totalOrderPrice) {
        if (totalOrderPrice >= 120_000) {
            return "샴페인";
        }
        return "없음";
    }
}
