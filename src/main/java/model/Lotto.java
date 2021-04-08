package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_SIZE_MUST_BE_SIX = "로또 번호는 6개여야 합니다.";

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    public static List<LottoNumber> generateLottoNumbers() {
        return Stream.generate(LottoNumber::generateRandomNumber)
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .collect(toList());
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        int lottoNumbersSize = (int) numbers.stream()
                .mapToInt(number -> number.getNumber())
                .distinct()
                .count();
        if (lottoNumbersSize != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_MUST_BE_SIX);
        }
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        String lottoNumbers = numbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .mapToObj(String::valueOf)
                .collect(joining(", "));
        return "[" + lottoNumbers + "]";
    }

}
