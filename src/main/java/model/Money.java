package model;

public class Money {
    private static final String MONEY_MUST_BE_POSITIVE = "구입금액은 0 이상의 숫자여야 합니다.";
    private static final int ZERO = 0;

    private final int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    private void validateMoney(int money) {
        if (money < ZERO) {
            throw new IllegalArgumentException(MONEY_MUST_BE_POSITIVE);
        }
    }
}
