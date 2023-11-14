package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderDTOTest {

    @DisplayName("주문 메뉴를 orders에 추가하라")
    @Test
    void validOrder() {
        // Given
        Map<String, Integer> orders = new HashMap<>();
        orders.put("양송이수프", 2);
        orders.put("티본스테이크", 1);

        // When & Then
        assertDoesNotThrow(() -> new OrderDTO(orders));
    }

    @DisplayName("음료만 주문했다면 예외를 발생시켜라")
    @ParameterizedTest
    @CsvSource({
            "'양송이수프,타파스,시저샐러드', true",
            "'티본스테이크,샴페인',true",
            "'제로콜라', false",
            "'레드와인,샴페인', false",
    })
    void validateOnlyJuice(String menuNames, boolean isValid) {
        // Given
        Map<String, Integer> orders = new HashMap<>();
        String[] menuArray = menuNames.split(",");
        for (String menu : menuArray) {
            orders.put(menu, 1);
        }

        // When & Then
        if (isValid) {
            assertDoesNotThrow(() -> new OrderDTO(orders));
        } else {
            assertThrows(IllegalArgumentException.class, () -> new OrderDTO(orders));
        }
    }

    @DisplayName("메뉴 개수가 20개가 넘으면 예외를 발생시켜라")
    @ParameterizedTest
    @CsvSource({
            "'양송이수프-5,타파스-3,시저샐러드-4', 12, true",
            "'양송이수프-7,타파스-12,시저샐러드-5', 24, false",
            "'양송이수프-1,타파스-1', 2, true",
            "'양송이수프-12,타파스-4,시저샐러드-4', 20, true",
            "'양송이수프-1', 1, true",
            "'양송이수프-0', 0, false"
    })
    void validateTotalMenuCount(String menuNames, int totalCount, boolean isValid) {
        // Given
        Map<String, Integer> orders = Arrays.stream(menuNames.split(","))
                .map(menu -> menu.split("-"))
                .collect(Collectors.toMap(
                        menuArray -> menuArray[0],
                        menuArray -> Integer.parseInt(menuArray[1])
                ));

        // When & Then
        if (isValid) {
            assertDoesNotThrow(() -> {
                OrderDTO orderDTO = new OrderDTO(orders);
                assertEquals(totalCount, orders.values().stream().mapToInt(Integer::intValue).sum());
            });
        }
        if(!isValid){
            assertThrows(IllegalArgumentException.class, () -> new OrderDTO(orders));
        }
    }
}
