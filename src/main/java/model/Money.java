package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

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

    public double calculateYield(Money totalBenefitMoney) {
        Money totalSpendMoney = calculateTotalSpendMoney();
        return totalBenefitMoney.amount
                .divide(totalSpendMoney.amount, YIELD_SCALE, RoundingMode.HALF_EVEN)
                .doubleValue();
    }

    public Money calculateTotalBenefitMoney(Map<Rank, Integer> statistics) {
        return Arrays.stream(Rank.values())
                .map(rank -> rank.calculateBenefit(statistics.get(rank)))
                .reduce(new Money(BigDecimal.ZERO), (previousBenefitMoney, nextBenefitMoney) -> new Money(previousBenefitMoney.amount.add(nextBenefitMoney.amount)));
    }

    private Money calculateTotalSpendMoney() {
        BigDecimal spendMoneyAmount = amount.remainder(LOTTO_PRICE);
        return new Money(amount.subtract(spendMoneyAmount));
    }

    public Money multiplyCount(int winningCount) {
        BigDecimal winningCountNumber = new BigDecimal(winningCount);
        return new Money(amount.multiply(winningCountNumber));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

}