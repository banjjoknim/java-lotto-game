package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final BigDecimal LOTTO_PRICE = new BigDecimal(1000);
    public static final int LOTTO_SIZE = 6;
    private static final String LOTTO_SIZE_MUST_BE_SIX = "로또 번호는 중복되지 않는 6개의 번호여야 합니다.";

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        int deduplicatedLottoNumbersSize = new HashSet<>(numbers).size();
        if (deduplicatedLottoNumbersSize != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_MUST_BE_SIX);
        }
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int calculateMatchCount(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::hasNumber)
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

}
