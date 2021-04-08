package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String LOTTO_NUMBER_MUST_BE_POSITIVE = "로또 번호는 양수여야 합니다.";
    private static final String LOTTO_NUMBER_MUST_BETWEEN_ONE_AND_FORTY_FIVE = "로또 번호는 1과 45 사이의 숫자여야 합니다.";

    @DisplayName("1부터 45 사이의 랜덤한 번호 생성을 테스트 한다.")
    @Test
    void generateRandomNumberTest() {
        // given

        // when
        int number = LottoNumber.generateRandomNumber();

        // then
        assertThat(number).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
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
    @ValueSource(ints = {46, 49, 77, 56, 99, 0})
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
    @ValueSource(ints = {1, 45, 37, 38, 27})
    void createLottoNumberTest(int number) {
        // given

        // when
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lottoNumber).isInstanceOf(LottoNumber.class);
        assertThat(lottoNumber.getNumber()).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
    }
}