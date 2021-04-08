package model;

import java.math.BigDecimal;

public class Money {
    private static final String MONEY_MUST_BE_POSITIVE = "구입금액은 0 이상의 숫자여야 합니다.";
    private static final int ZERO = 0;

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        validateMoney(amount);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void validateMoney(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < ZERO) {
            throw new IllegalArgumentException(MONEY_MUST_BE_POSITIVE);
        }
    }
}
