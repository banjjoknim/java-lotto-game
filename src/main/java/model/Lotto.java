package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lotto {
    private static final String LOTTO_NUMBERS_SIZE_MUST_BE_SIX = "로또 번호는 6개의 숫자여야 합니다.";
    private static final String LOTTO_NUMBERS_MUST_NOT_OVERLAP = "로또 번호는 중복된 번호가 존재하면 안됩니다.";
    public static final int PRICE = 1000;
    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }

    public static List<LottoNumber> createLottoNumbers() {
        try {
            List<LottoNumber> lottoNumbers = createNumbers();
            validateLottoNumbers(lottoNumbers);
            return lottoNumbers;
        } catch (IllegalArgumentException e) {
            return createLottoNumbers();
        }
    }

    private static List<LottoNumber> createNumbers() {
        return Stream.generate(LottoNumber::new)
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(toList());
    }

    public static void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateOverlap(lottoNumbers);
    }

    private static void validateOverlap(List<LottoNumber> lottoNumbers) {
        int distinctCount = (int) lottoNumbers.stream()
                .mapToInt(lottoNumber -> lottoNumber.getNumber())
                .distinct()
                .count();
        if (distinctCount != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_MUST_NOT_OVERLAP);
        }
    }

    private static void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE_MUST_BE_SIX);
        }
    }

}
