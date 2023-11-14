package christmas.view;

import christmas.domain.Menu;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String PRINT_ORDER_FORMAT = "%s %d개";
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");
    private static final String TOTAL_DISCOUNT_FORMAT = "크리스마스 디데이 할인: -%s원\n평일 할인: -%s원\n특별 할인: -%s원\n증정 이벤트: -%s원\n\n<총혜택 금액>\n-%s원\n";
    private static final String TOTAL_NO_DISCOUNT_FORMAT = "없음\n\n<총혜택 금액>\n0원";

    public void printPreview(final int date) {
        String message = String.format(PREVIEW_FORMAT, date);
        System.out.println(message + "\n");
    }

    public void printOrderList(final Map<String, Integer> orders) {
        System.out.println("<주문 메뉴>");
        orders.forEach((menu, quantity) -> System.out.println(String.format(PRINT_ORDER_FORMAT, menu, quantity)));
    }

    public void printTotalOrderPrice(final int totalOrderPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(totalOrderPrice);
    }

    public void printPresent(final String present) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(present);
    }

    public int printTotalDiscount(final int dateDiscount, final int dayDiscount, final int specialDiscount,
                             final String present) {
        System.out.println("\n<혜택 내역>");
        if (isDiscountExist(dateDiscount, dayDiscount, specialDiscount, present)) {
            int presentPrice = Menu.findMenuByName(present).getMenuPrice();
            int totalDiscountPrice = dateDiscount + dayDiscount + specialDiscount + presentPrice;
            String totalDiscountMessage = String.format(TOTAL_DISCOUNT_FORMAT, MONEY_FORMAT.format(dateDiscount),
                    MONEY_FORMAT.format(dayDiscount), MONEY_FORMAT.format(specialDiscount),
                    MONEY_FORMAT.format(presentPrice), MONEY_FORMAT.format(totalDiscountPrice));
            System.out.println(totalDiscountMessage);
            return totalDiscountPrice;
        }
        if (!isDiscountExist(dateDiscount, dayDiscount, specialDiscount, present)) {
            System.out.println(TOTAL_NO_DISCOUNT_FORMAT);
        }
        return 0;
    }

    private boolean isDiscountExist(final int dateDiscount, final int dayDiscount, final int specialDiscount,
                                    final String present) {
        return isDateDiscount(dateDiscount) || isDayDiscount(dayDiscount) || isSpecialDiscount(specialDiscount)
                || isPresentDiscount(present);
    }

    private boolean isDateDiscount(final int dateDiscount) {
        return dateDiscount != 0;
    }

    private boolean isDayDiscount(final int dayDiscount) {
        return dayDiscount != 0;
    }

    private boolean isSpecialDiscount(final int specialDiscount) {
        return specialDiscount != 0;
    }

    private boolean isPresentDiscount(final String present) {
        return !present.equals("없음");
    }

    public void printAfterDiscount(final int totalOrderPrice, final int totalDiscount, final int presentPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        int afterPrice = totalOrderPrice - totalDiscount + presentPrice;
        System.out.println(MONEY_FORMAT.format(afterPrice) + "원");
    }

    public void printBadge(final String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
