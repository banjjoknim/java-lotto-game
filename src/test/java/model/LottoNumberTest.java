package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberTest {

    @DisplayName("생성된 로또 번호가 1에서 45 사이의 숫자인지 테스트한다.")
    @Test
    void lottoNumberIsBetweenLottoMinNumberAndLottoMaxNumberTest() {
        LottoNumber lottoNumber = new LottoNumber();
        assertThat(lottoNumber.getNumber()).isBetween(LottoNumber.LOTTO_MIN_NUMBER, LottoNumber.LOTTO_MAX_NUMBER);
    }

}