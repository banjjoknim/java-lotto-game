package model;

import java.util.concurrent.ThreadLocalRandom;

public class LottoNumber {
    private static final int ZERO = 0;
    private static final int LOTTO_NUMBER_BOUND = 46;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String LOTTO_NUMBER_MUST_BE_POSITIVE = "로또 번호는 양수여야 합니다.";
    private static final String LOTTO_NUMBER_MUST_BETWEEN_ONE_AND_FORTY_FIVE = "로또 번호는 1과 45 사이의 숫자여야 합니다.";

    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        validateNumberIsPositive(number);
        validateNumberIsValid(number);
    }

    private void validateNumberIsPositive(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BE_POSITIVE);
        }
    }

    private void validateNumberIsValid(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BETWEEN_ONE_AND_FORTY_FIVE);
        }
    }

    public static int generateLottoNumber() {
        return ThreadLocalRandom.current().nextInt(MIN_LOTTO_NUMBER, LOTTO_NUMBER_BOUND);
    }

}
