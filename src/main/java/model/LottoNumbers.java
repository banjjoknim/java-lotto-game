package model;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoNumbers {
    private static final String LOTTO_NUMBERS_SIZE_MUST_BE_SIX = "로또 번호는 6개의 숫자여야 합니다.";
    private static final String LOTTO_NUMBERS_MUST_NOT_OVERLAP = "로또 번호는 중복된 번호가 존재하면 안됩니다.";
    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static List<Integer> createLottoNumbers() {
        try {
            List<Integer> lottoNumbers = createNumbers();
            validateLottoNumbers(lottoNumbers);
            return lottoNumbers;
        } catch (IllegalArgumentException e) {
            return createLottoNumbers();
        }
    }

    private static List<Integer> createNumbers() {
        return Stream.generate(LottoNumber::new)
                .limit(LOTTO_NUMBERS_SIZE)
                .mapToInt(LottoNumber::getNumber)
                .boxed()
                .collect(toList());
    }

    public static void validateLottoNumbers(List<Integer> lottoNumbers) {
        validateSize(lottoNumbers);
        validateOverlap(lottoNumbers);
    }

    private static void validateOverlap(List<Integer> lottoNumbers) {
        int distinctCount = (int) lottoNumbers.stream()
                .distinct()
                .count();
        if (distinctCount != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_MUST_NOT_OVERLAP);
        }
    }

    private static void validateSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE_MUST_BE_SIX);
        }
    }

}
