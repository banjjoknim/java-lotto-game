package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_SIZE_MUST_BE_SIX = "로또 번호는 중복되지 않는 6개의 번호여야 합니다.";
    public static final BigDecimal LOTTO_PRICE = new BigDecimal(1000);

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

    public static Lotto generateLotto() {
        try {
            List<LottoNumber> lottoNumbers = Stream.generate(LottoNumber::generateRandomNumber)
                    .limit(LOTTO_SIZE)
                    .collect(toList());
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException e) {
            return generateLotto();
        }
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int calculateMatchCount(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(number -> winningLotto.hasNumber(number))
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

}
