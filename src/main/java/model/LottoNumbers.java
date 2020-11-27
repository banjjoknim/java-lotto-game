package model;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoNumbers {
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    public static List<Integer> createNumbers() {
        return Stream.generate(createRandomNumber)
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(toList());
    }

    private static Supplier<Integer> createRandomNumber = () -> LOTTO_MIN_NUMBER + (int) (Math.random() * LOTTO_MAX_NUMBER);
}
