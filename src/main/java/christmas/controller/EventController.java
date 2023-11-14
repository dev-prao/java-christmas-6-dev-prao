package christmas.controller;

import christmas.domain.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

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

        int totalOrderPrice = OrderDTO.totalOrderPrice(orders);
        showTotalOrderPrice(totalOrderPrice);

        String present = PresentEvent.getPresent(totalOrderPrice);
        outputView.printPresent(present);

        DiscountDTO discountDTO = DiscountDTO.calculateDiscount(date, orders, totalOrderPrice);
        showDiscountList(discountDTO, totalOrderPrice);
        showEventBadge(discountDTO.totalDiscount());
    }

    private int getVisitDate() {
        while (true) {
            try {
                DateDTO dateDTO = new DateDTO(inputView.inputVisitDate());
                return dateDTO.date();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<String, Integer> getInputOrders() {
        while (true) {
            try {
                OrderDTO orderDTO = new OrderDTO(inputView.inputOrders());
                return orderDTO.orders();
            } catch (IllegalArgumentException | IllegalStateException e) {
                handleException(e);
            }
        }
    }

    private void handleException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            System.out.println(e.getMessage());
        }
        if (e instanceof IllegalStateException) {
            System.out.println(e.getMessage());
        }
    }


    private void showPreviewAndOrderList(final int date, final Map<String, Integer> orders) {
        outputView.printPreview(date);
        outputView.printOrderList(orders);
    }

    private void showTotalOrderPrice(final int totalOrderPrice) {
        outputView.printTotalOrderPrice(totalOrderPrice);
    }

    private void showDiscountList(final DiscountDTO discountDTO, final int totalOrderPrice) {
        outputView.printTotalDiscount(discountDTO.dateDiscount(), discountDTO.dayDiscount(),
                discountDTO.specialDiscount(), discountDTO.presentPrice());
        outputView.printAfterDiscount(totalOrderPrice, discountDTO.totalDiscount(), discountDTO.presentPrice());
    }

    private void showEventBadge(int totalDiscount) {
        String badge = EventBadge.create(totalDiscount).getBadge();
        outputView.printBadge(badge);
    }
}
