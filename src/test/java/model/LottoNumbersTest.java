package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumbersTest {

    @DisplayName("랜덤하게 생성된 번호가 총 6개인지 테스트한다.")
    @Test
    void numbersSizeIsSixTest() {
        List<Integer> lottoNumbers = LottoNumbers.createNumbers();
        assertThat(lottoNumbers.size()).isEqualTo(LottoNumbers.LOTTO_NUMBERS_SIZE);
    }

    @DisplayName("랜덤하게 생성된 번호가 각각 1부터 45사이의 숫자인지 테스트한다.")
    @Test
    void createNumbersTest() {
        List<Integer> lottoNumbers = LottoNumbers.createNumbers();
        boolean allNumberIsBetweenOneAndFourtyFive = lottoNumbers.stream()
                .allMatch(numberIsBetweenOneAndFourtyFive);
        assertThat(allNumberIsBetweenOneAndFourtyFive).isTrue();

    }

    private static Predicate<Integer> numberIsBetweenOneAndFourtyFive =
            (number) -> LottoNumbers.LOTTO_MIN_NUMBER <= number && number <= LottoNumbers.LOTTO_MAX_NUMBER;
}