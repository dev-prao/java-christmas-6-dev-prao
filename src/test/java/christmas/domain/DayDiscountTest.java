package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DayDiscountTest {

    @DisplayName("할인이 적용되는 메인 메뉴와 디저트 메뉴에 대한 할인을 계산하라")
    @ParameterizedTest
    @CsvSource({
            "1, '티본스테이크', 1, '초코케이크', 1, 2023",  // 금요일, 메인 메뉴 할인
            "2, '바비큐립', 2, '아이스크림', 2, 4046",       // 토요일, 메인 메뉴 할인
            "3, '해산물파스타', 1, '초코케이크', 1, 2023",      // 일요일, 디저트 메뉴 할인
            "4, '크리스마스파스타', 3, '초코케이크', 2, 4046", // 월요일, 디저트 메뉴 할인
            "5, '티본스테이크', 1, '아이스크림', 2, 4046",    // 화요일, 디저트 메뉴 할인
            "6, '타파스', 4, '초코케이크', 1, 2023",            // 수요일, 디저트 메뉴 할인
            "7, '시저샐러드', 2, '초코케이크', 2, 4046",        // 목요일, 디저트 메뉴 할인
    })
    void getDayDiscount(int date, String menu1, int quantity1, String menu2, int quantity2, int expectedDiscount) {
        // Given
        Map<String, Integer> orders = Map.of(menu1, quantity1, menu2, quantity2);

        // When
        int discount = DayDiscount.getDayDiscount(date, orders);

        // Then
        assertEquals(expectedDiscount, discount);
    }
}
