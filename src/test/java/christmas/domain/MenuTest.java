package christmas.domain;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {

    @DisplayName("메뉴 이름으로 올바른 메뉴를 찾아올 수 있는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"})
    void findMenuByName(String menuName) {
        // When
        Optional<Menu> foundMenu = Menu.findMenuByName(menuName);

        // Then
        assertTrue(foundMenu.isPresent());
        assertEquals(menuName, foundMenu.get().name());
    }

    @DisplayName("존재하지 않는 메뉴 이름으로 찾을 경우 빈 Optional을 반환하는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"라면", "햄버거", "피자"})
    void findNonexistentMenuByName(String menuName) {
        // When
        Optional<Menu> foundMenu = Menu.findMenuByName(menuName);

        // Then
        assertTrue(foundMenu.isEmpty());
    }
}
