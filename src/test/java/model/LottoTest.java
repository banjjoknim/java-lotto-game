package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoTest {
    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_SIZE_MUST_BE_SIX = "로또 번호는 중복되지 않는 6개의 번호여야 합니다.";

    private static List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUpLottoNumbers() {
        lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .collect(toList());
    }

    @DisplayName("Lotto 생성시 중복된 번호 예외를 테스트한다.")
    @Test
    void validateLottoNumbersTest() {
        // given
        List<LottoNumber> numbers = Arrays.asList(1, 2, 3, 4, 5, 5).stream()
                .map(LottoNumber::new)
                .collect(toList());

        // when

        // then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_SIZE_MUST_BE_SIX);
    }

    @DisplayName("올바른 값으로 Lotto 생성을 테스트 한다.")
    @Test
    void generateLottoTest() {
        // given

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertAll(
                () -> assertThat(lotto).isInstanceOf(Lotto.class),
                () -> assertThat(lotto.getNumbers()).hasSize(LOTTO_SIZE)
        );
    }

    @DisplayName("랜덤한 값으로 Lotto 생성을 테스트 한다.")
    @Test
    void generateLottoWithRandomNumberTest() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        // when
        Lotto lotto = Lotto.generateLotto(numberGenerator);

        // then
        assertAll(
                () -> assertThat(lotto).isInstanceOf(Lotto.class),
                () -> assertThat(lotto.getNumbers()).hasSize(LOTTO_SIZE)
        );
    }

    @DisplayName("Lotto가 LottoNumber를 포함하는지 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void hasNumberTest(int number) {
        // given

        // when
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lotto.hasNumber(lottoNumber)).isTrue();
    }

    @DisplayName("Lotto가 LottoNumber를 포함하지 않는지 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11, 15})
    void hasNotNumberTest(int number) {
        // given

        // when
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lotto.hasNumber(lottoNumber)).isFalse();
    }

}