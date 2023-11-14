package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.parser.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserTest {

    private static final String EMPTY_ERROR_MESSAGE = "[ERROR] 값이 비어있습니다. 다시 입력해주세요.";
    private static final String INVALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("유효한 날짜 입력 테스트")
    @Test
    void parseDateValidInput() {
        // Given
        String input = "10";

        // When & Then
        assertDoesNotThrow(() -> Parser.parseDate(input));
    }

    @DisplayName("유효하지 않은 날짜 입력 테스트")
    @ParameterizedTest
    @CsvSource({
            "'', " + INVALID_DATE_ERROR_MESSAGE,
            "'abc', " + INVALID_DATE_ERROR_MESSAGE,
            "'-5', " + INVALID_DATE_ERROR_MESSAGE,
            "'12a', " + INVALID_DATE_ERROR_MESSAGE,
            "' ', " + INVALID_DATE_ERROR_MESSAGE
    })
    void parseDateInvalidInput(String input, String expectedErrorMessage) {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Parser.parseDate(input));
        assertErrorMessageEquals(expectedErrorMessage, exception.getMessage());
    }

    @DisplayName("유효한 주문 입력 테스트")
    @Test
    void parseOrderValidInput() {
        // Given
        String input = "양송이수프-2, 타파스-1";

        // When & Then
        assertDoesNotThrow(() -> Parser.parseOrder(input));
    }

    @DisplayName("유효하지 않은 주문 입력 테스트")
    @ParameterizedTest
    @CsvSource({
            "'치킨-1,피자-1,'",
            "'양송이수프-2,양송이수프-1,'"
    })
    void parseOrderInvalidInput(String input) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> Parser.parseOrder(input), INVALID_ORDER_ERROR_MESSAGE);
    }

    @DisplayName("빈 값 검증 테스트")
    @Test
    void validateEmptyValidInput() {
        // Given
        String input = "Valid Input";

        // When & Then
        assertDoesNotThrow(() -> Parser.validateEmpty(input));
    }

    @DisplayName("빈 값 입력 시 예외 테스트")
    @Test
    void validateEmptyInvalidInput() {
        // Given
        String input = "";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Parser.validateEmpty(input));
        assertErrorMessageEquals(EMPTY_ERROR_MESSAGE, exception.getMessage());
    }

    private void assertErrorMessageEquals(String expected, String actual) {
        expected = expected.trim();
        actual = actual.trim();
        assert expected.equals(actual) : String.format("Expected error message: '%s', but got: '%s'", expected, actual);
    }
}
