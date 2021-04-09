package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    private static final int LOTTO_SIZE = 6;

    private List<LottoNumber> setUpLottoNumbers() {
        return Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .collect(toList());
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

    @DisplayName("Lotto 생성을 테스트 한다.")
    @Test
    void generateLottoTest() {
        // given
        List<LottoNumber> lottoNumbers = Lotto.generateLottoNumbers();

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertThat(lotto).isInstanceOf(Lotto.class);
        assertThat(lotto.getNumbers()).hasSize(LOTTO_SIZE);
    }

    @DisplayName("Lotto가 LottoNumber를 포함하는지 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void hasNumberTest(int number) {
        // given
        List<LottoNumber> lottoNumbers = setUpLottoNumbers();

        // when
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lotto.hasNumber(lottoNumber)).isTrue();
    }

    @DisplayName("Lotto가 LottoNumber를 포함하지 않는지 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 15, 10, 11})
    void hasNotNumberTest(int number) {
        // given
        List<LottoNumber> lottoNumbers = setUpLottoNumbers();

        // when
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lotto.hasNumber(lottoNumber)).isFalse();
    }

}