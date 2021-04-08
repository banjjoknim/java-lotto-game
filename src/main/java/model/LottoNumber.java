package model;

public class LottoNumber {
    private static final int ZERO = 0;
    private static final String LOTTO_NUMBER_MUST_BE_POSITIVE = "로또 번호는 양수여야 합니다.";

    private final int number;

    public LottoNumber(int number) {
        this.number = number;
    }

    private void validateNumberIsPositive(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BE_POSITIVE);
        }
    }
}
