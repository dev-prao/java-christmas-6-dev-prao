package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountTest {

    @DisplayName("특별 할인이 정상적으로 계산되는지 확인하라")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void getSpecialDiscount_ApplicableDate(int date) {
        // When
        int discount = SpecialDiscount.getSpecialDiscount(date);

        // Then
        assertEquals(1_000, discount);
    }

    @DisplayName("특별 할인이 적용되지 않는 날짜인지 확인하라")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 9, 16, 23, 30})
    void getSpecialDiscount_NonApplicableDate(int date) {
        // When
        int discount = SpecialDiscount.getSpecialDiscount(date);

        // Then
        assertEquals(0, discount);
    }
}
