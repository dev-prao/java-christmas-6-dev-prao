package christmas.domain;

import java.util.Map;

public record DiscountDTO(
        int dateDiscount,
        int dayDiscount,
        int specialDiscount,
        String present
) {
    public static DiscountDTO calculateDiscount(int date, Map<String, Integer> orders, int totalOrderPrice) {
        int dateDiscount = DateDiscount.getDateDiscount(date);
        int dayDiscount = DayDiscount.getDayDiscount(date, orders);
        int specialDiscount = SpecialDiscount.getSpecialDiscount(date);
        String present = PresentEvent.getPresent(totalOrderPrice);

        return new DiscountDTO(dateDiscount, dayDiscount, specialDiscount, present);
    }

    public int totalDiscount() {
        int presentPrice = Menu.findMenuByName(present)
                .map(Menu::getMenuPrice)
                .orElse(0);

        return dateDiscount + dayDiscount + specialDiscount + presentPrice;
    }

    public int presentPrice() {
        return Menu.findMenuByName(present)
                .map(Menu::getMenuPrice)
                .orElse(0);
    }
}
