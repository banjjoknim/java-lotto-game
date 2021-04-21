package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static model.Lotto.LOTTO_PRICE;

public class Money {
    private static final String MONEY_MUST_BE_POSITIVE = "구입금액은 0 이상의 숫자여야 합니다.";
    private static final int IS_NEGATIVE = -1;
    private static final int YIELD_SCALE = 3;

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(BigDecimal amount) {
        int status = amount.compareTo(BigDecimal.ZERO);
        if (status == IS_NEGATIVE) {
            throw new IllegalArgumentException(MONEY_MUST_BE_POSITIVE);
        }
    }

    public int calculateIssueCount() {
        return amount.divide(LOTTO_PRICE).intValue();
    }

    public double calculateYield(BigDecimal totalBenefit) {
        BigDecimal totalSpendMoney = calculateTotalSpendMoney();
        return totalBenefit.divide(totalSpendMoney, YIELD_SCALE, RoundingMode.HALF_EVEN).doubleValue();
    }

    public BigDecimal calculateTotalSpendMoney() {
        return amount.subtract(amount.remainder(LOTTO_PRICE));
    }

    public BigDecimal multiplyCount(int winningCount) {
        return amount.multiply(new BigDecimal(winningCount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

}