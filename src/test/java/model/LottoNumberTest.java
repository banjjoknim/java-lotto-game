package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoNumberTest {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String LOTTO_NUMBER_MUST_BE_POSITIVE = "로또 번호는 양수여야 합니다.";
    private static final String LOTTO_NUMBER_MUST_BETWEEN_ONE_AND_FORTY_FIVE = "로또 번호는 1과 45 사이의 숫자여야 합니다.";

    @DisplayName("1부터 45 사이의 랜덤한 번호 생성을 테스트 한다.")
    @Test
    void generateRandomNumberTest() {
        // given
        NumberGenerator strategy = new RandomNumberGenerator();

        // when
        LottoNumber lottoNumber = LottoNumber.getLottoNumberFromCache(strategy.generate());

        // then
        assertThat(lottoNumber.getNumber()).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
    }

    @DisplayName("음수로 LottoNumber 생성시 예외를 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -8, -17, -20})
    void validateLottoNumberIsPositiveTest(int number) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_MUST_BE_POSITIVE);
    }

    @DisplayName("0또는 1과 45의 이외의 양수로 LottoNumber 생성시 예외를 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 56, 46, 49, 77, 99})
    void validateLottoNumberIsValidTest(int number) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_MUST_BETWEEN_ONE_AND_FORTY_FIVE);
    }

    @DisplayName("유효한 값으로 LottoNumber 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 27, 37, 38, 45})
    void createLottoNumberTest(int number) {
        // given

        // when
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertAll(
                () -> assertThat(lottoNumber).isInstanceOf(LottoNumber.class),
                () -> assertThat(lottoNumber.getNumber()).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER),
                () -> assertThat(lottoNumber.getNumber()).isEqualTo(number)
        );
    }

    @DisplayName("유효하지 않은 값을 생성하는 전략으로 LottoNumber 생성을 테스트 한다.")
    @Test
    void generateLottoNumberWithInvalidNumber() {
        // given

        int numberLessThanMinLottoNumber = new LessThanLottoMinNumberGenerator().generate();
        int numberMoreThanMaxLottoNumber = new MoreThanLottoMaxNumberGenerator().generate();

        // when

        // then
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumber(numberLessThanMinLottoNumber)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumber(numberMoreThanMaxLottoNumber))
        );
    }

}