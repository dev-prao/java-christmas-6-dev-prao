package christmas.dto;

public record DateDTO(
        int date
) {
    private static final String ERROR_FORMAT = "[ERROR] %s";
    private static final String INVALID_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    public DateDTO {
        validateDate(date);
    }

    private void validateDate(final int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, INVALID_VISIT_DATE));
        }
    }
}
