package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumbersTest {
    private static final int LOTTO_NUMBERS_SIZE = 6;

    @DisplayName("랜덤하게 생성된 번호가 총 6개인지 테스트한다.")
    @Test
    void numbersSizeIsSixTest() {
        List<Integer> lottoNumbers = LottoNumbers.createNumbers();
        assertThat(lottoNumbers.size()).isEqualTo(LOTTO_NUMBERS_SIZE);
    }

    @DisplayName("랜덤하게 생성된 번호가 각각 1부터 45사이의 숫자인지 테스트한다.")
    @Test
    void createNumbersTest() {
        List<Integer> lottoNumbers = LottoNumbers.createNumbers();
        boolean allNumberIsBetweenOneAndFourtyFive = lottoNumbers.stream()
                .allMatch(numberIsBetweenOneAndFourtyFive);
        assertThat(allNumberIsBetweenOneAndFourtyFive).isTrue();

    }

    private static Predicate<Integer> numberIsBetweenOneAndFourtyFive = (number) -> 1 <= number && number <= 45;
}