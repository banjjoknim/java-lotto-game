package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoNumberTest {

    @DisplayName("음수로 LottoNumber 생성시 예외를 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -8, -17, -20})
    void validateLottoNumberIsPositiveTest(int number) {
        // given

        // when

        // then
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 양수여야 합니다.");
    }

    @DisplayName("0또는 1과 45의 이외의 양수로 LottoNumber 생성시 예외를 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 56, 46, 49, 77, 99})
    void validateLottoNumberIsValidTest(int number) {
        // given

        // when

        // then
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1과 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("유효한 값으로 LottoNumber 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 27, 37, 38, 45})
    void createLottoNumberTest(int number) {
        // given

        // when
        LottoNumber lottoNumber = LottoNumber.from(number);

        // then
        assertAll(
                () -> assertThat(lottoNumber).isInstanceOf(LottoNumber.class),
                () -> assertThat(lottoNumber.getNumber()).isBetween(1, 45),
                () -> assertThat(lottoNumber.getNumber()).isEqualTo(number)
        );
    }

}