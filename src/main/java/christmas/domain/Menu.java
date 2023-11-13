package christmas.domain;

import java.util.Arrays;

public enum Menu {
    양송이수프("에피타이저", 6_000),
    타파스("에피타이저", 5_500),
    시저샐러드("에피타이저", 8_000),

    티본스테이크("메인", 55_000),
    바비큐립("메인", 54_000),
    해산물파스타("메인", 35_000),
    크리스마스파스타("메인", 25_000),

    초코케이크("디저트", 15_000),
    아이스크림("디저트", 5_000),

    제로콜라("음료", 3_000),
    레드와인("음료", 60_000),
    샴페인("음료", 25_000);

    private final String category;
    private final int menuPrice;

    Menu(final String category, final int menuPrice) {
        this.category = category;
        this.menuPrice = menuPrice;
    }

    public static Menu findMenuByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equalsIgnoreCase(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 메뉴를 찾을 수 없습니다."));
    }

    public String getCategory() {
        return category;
    }

    public int getMenuPrice() {
        return menuPrice;
    }
}
