package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_SIZE_MUST_BE_SIX = "로또 번호는 중복되지 않는 6개의 번호여야 합니다.";
    private static final String SEPARATOR = ",";
    public static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        int lottoNumbersSize = new HashSet<>(numbers).size();
        if (lottoNumbersSize != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_MUST_BE_SIX);
        }
    }

    public static List<LottoNumber> generateLottoNumbers() {
        return Stream.generate(LottoNumber::generateRandomNumber)
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .collect(toList());
    }

    public static Lotto generateLottoByInput(String inputLottoNumbers) {
        List<LottoNumber> lottoNumbers = generateLottoNumbersByInput(inputLottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static List<LottoNumber> generateLottoNumbersByInput(String inputWinningLottoNumbers) {
        return Arrays.stream(inputWinningLottoNumbers.split(SEPARATOR))
                .map(Integer::valueOf)
                .map(LottoNumber::new)
                .collect(toList());
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
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
