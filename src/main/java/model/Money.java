package model;

public class Money {
    private final static int ZERO = 0;

    private final int money;

    public Money(int money) {
        validateMoneyIsPositive(money);
        this.money = money;
    }

    private void validateMoneyIsPositive(int money) {
        if (money < ZERO) {
            throw new IllegalArgumentException();
        }
    }

    public int getMoney() {
        return money;
    }
}
