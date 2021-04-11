package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int ZERO = 0;
    private static final int LOTTO_NUMBER_BOUND = 46;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String LOTTO_NUMBER_MUST_BE_POSITIVE = "로또 번호는 양수여야 합니다.";
    private static final String LOTTO_NUMBER_MUST_BETWEEN_MIN_LOTTO_NUMBER_AND_MAX_LOTTO_NUMBER = "로또 번호는 1과 45 사이의 숫자여야 합니다.";

    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> CACHE.put(number, new LottoNumber(number)));
    }

    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        validatePositiveNumber(number);
        validateBetweenMinLottoNumberAndMaxLottoNumber(number);
    }

    private void validatePositiveNumber(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BE_POSITIVE);
        }
    }

    private void validateBetweenMinLottoNumberAndMaxLottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BETWEEN_MIN_LOTTO_NUMBER_AND_MAX_LOTTO_NUMBER);
        }
    }

    public static LottoNumber generateRandomNumber() {
        return CACHE.get(ThreadLocalRandom.current().nextInt(MIN_LOTTO_NUMBER, LOTTO_NUMBER_BOUND));
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
