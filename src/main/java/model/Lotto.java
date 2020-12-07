package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Lotto {
    private static final String LOTTO_NUMBERS_SIZE_MUST_BE_SIX = "로또 번호는 6개의 숫자여야 합니다.";
    private static final String LOTTO_NUMBERS_MUST_BETWEEN_ONE_AND_FOURTYFIVE = "로또 번호는 1부터 45 사이의 숫자로 구성되어야 합니다.";
    private static final String LOTTO_NUMBERS_MUST_NOT_OVERLAP = "로또 번호는 중복된 번호가 존재하면 안됩니다.";
    public static final int PRICE = 1000;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != LottoNumbers.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE_MUST_BE_SIX);
        }
        if (numbers.stream().anyMatch(isBetweenOneAndFourtyFive.negate())) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_MUST_BETWEEN_ONE_AND_FOURTYFIVE);
        }
        int distinctCount = (int) numbers.stream()
                .distinct()
                .count();
        if (distinctCount != LottoNumbers.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_MUST_NOT_OVERLAP);
        }
    }

    private Predicate<Integer> isBetweenOneAndFourtyFive =
            (Integer number) -> LottoNumbers.LOTTO_MIN_NUMBER <= number && number <= LottoNumbers.LOTTO_MAX_NUMBER;

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }
}
