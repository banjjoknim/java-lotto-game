package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_SIZE_MUST_BE_SIX = "로또 번호는 6개여야 합니다.";

    @DisplayName("Lotto 생성시 중복된 번호 예외를 테스트한다.")
    @Test
    void validateLottoNumbersTest() {
        // given
        List<LottoNumber> numbers = Arrays.asList(1, 2, 3, 4, 5, 5)
                .stream()
                .map(LottoNumber::new)
                .collect(toList());

        // when

        // then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_SIZE_MUST_BE_SIX);
    }

    @DisplayName("LottoNumbers 생성을 테스트 한다.")
    @Test
    void generateLottoNumbersTest() {
        // given

        // when
        List<LottoNumber> lottoNumbers = Lotto.generateLottoNumbers();

        // then
        assertThat(lottoNumbers).hasSize(LOTTO_SIZE);
    }
}