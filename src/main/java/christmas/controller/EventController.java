package christmas.controller;

import christmas.InputValidator;
import christmas.domain.Badge;
import christmas.domain.DateDiscount;
import christmas.domain.DayDiscount;
import christmas.domain.Menu;
import christmas.domain.PresentEvent;
import christmas.domain.SpecialDiscount;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int date = getVisitDate();
        Map<String, Integer> orders = getInputOrders();
        showPreviewAndOrderList(date, orders);
        int totalOrderPrice = getTotalOrderPrice(orders);
        showTotalOrderPrice(totalOrderPrice);
        showPresentMenu(totalOrderPrice);
        showDiscountList(date, orders, totalOrderPrice);
    }

    private int getVisitDate() {
        while (true) {
            try {
                int date = inputView.inputVisitDate();
                InputValidator.validateDate(date);
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<String, Integer> getInputOrders() {
        while (true) {
            try {
                Map<String, Integer> orders = inputView.inputOrders();
                InputValidator.validateMenuNames(orders);
                Set<String> categories = getCategoriesFromOrders(orders);
                InputValidator.validateOnlyJuice(categories);
                int orderCount = orders.values().stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                InputValidator.validateMaxMenuCount(orderCount);
                InputValidator.validateOrder(orders);
                return orders;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Set<String> getCategoriesFromOrders(Map<String, Integer> orders) {
        return orders.keySet().stream()
                .map(menuName -> Menu.findMenuByName(menuName.toUpperCase()))
                .flatMap(Optional::stream)  // Optional을 평면화하여 처리
                .map(Menu::getCategory)
                .collect(Collectors.toSet());
    }

    private void showPreviewAndOrderList(final int date, final Map<String, Integer> orders) {
        outputView.printPreview(date);
        outputView.printOrderList(orders);
    }

    private int getTotalOrderPrice(Map<String, Integer> orders) {
        int totalOrderPrice = 0;

        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            String stringName = entry.getKey();
            int quantity = entry.getValue();
            int menuPrice = Menu.findMenuByName(stringName)
                    .map(Menu::getMenuPrice)
                    .orElse(0);
            totalOrderPrice += menuPrice * quantity;
        }
        return totalOrderPrice;
    }

    private void showTotalOrderPrice(final int totalOrderPrice) {
        outputView.printTotalOrderPrice(totalOrderPrice);
    }

    private void showPresentMenu(final int totalOrderPrice) {
        String present = getPresent(totalOrderPrice);
        outputView.printPresent(present);
    }

    private String getPresent(final int totalOrderPrice) {
        return PresentEvent.getPresent(totalOrderPrice);
    }

    private void showDiscountList(final int date, final Map<String, Integer> orders, final int totalOrderPrice) {
        int dateDiscount = getDateDiscount(date);
        int dayDiscount = getDayDiscount(date, orders);
        int specialDiscount = getSpecialDiscount(date);
        String present = getPresent(totalOrderPrice);
        int presentPrice = Menu.findMenuByName(present)
                .map(Menu::getMenuPrice)
                .orElse(0);
        int totalDiscount = outputView.printTotalDiscount(dateDiscount, dayDiscount, specialDiscount, present);
        outputView.printAfterDiscount(totalOrderPrice, totalDiscount, presentPrice);
        String badge = getEventBadge(totalDiscount);
        outputView.printBadge(badge);
    }

    private int getDateDiscount(final int date) {
        return DateDiscount.getDateDiscount(date);
    }

    private int getDayDiscount(final int date, Map<String, Integer> orders) {
        return DayDiscount.getDayDiscount(date, orders);
    }

    private int getSpecialDiscount(final int date) {
        return SpecialDiscount.getSpecialDiscount(date);
    }

    private String getEventBadge(final int totalDiscount) {
        return Badge.getBadge(totalDiscount);
    }
}
