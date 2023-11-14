package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateDiscountTest {

    @DisplayName("1일부터 25일 사이에는 할인을 적용한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25})
    void getDateDiscount_DiscountDate(int date) {
        // When
        int discount = DateDiscount.getDateDiscount(date);

        // Then
        int expectedDiscount = (date - 1) * 100 + 1000;
        assertEquals(expectedDiscount, discount);
    }

    @DisplayName("26일 이후에는 할인을 적용하지 않는다")
    @ParameterizedTest
    @ValueSource(ints = {26, 30, 31})
    void getDateDiscount_NotDiscountDate(int date) {
        // When
        int discount = DateDiscount.getDateDiscount(date);

        // Then
        assertEquals(0, discount);
    }
}
