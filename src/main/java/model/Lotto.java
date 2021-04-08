package model;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static List<LottoNumber> generateLottoNumbers() {
        return Stream.generate(LottoNumber::generateRandomNumber)
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .collect(toList());
    }

}
