package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class WinningLottoTest {
    private static final String BONUS_BALL_NUMBER_IS_DUPLICATE = "보너스 볼 번호가 당첨 로또 번호와 중복됩니다.";

    private List<LottoNumber> setUpLottoNumbers() {
        return Arrays.asList(1, 3, 5, 10, 42, 35)
                .stream()
                .map(LottoNumber::new)
                .collect(toList());
    }

    @DisplayName("WinningLotto 생성시 보너스 번호 중복 예외를 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10, 42, 35})
    void validateBonusNoTest(int bonusNo) {
        // given
        List<LottoNumber> lottoNumbers = setUpLottoNumbers();
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNo);

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningLotto(lotto, bonusLottoNumber))
                .withMessage(BONUS_BALL_NUMBER_IS_DUPLICATE);
    }

    @DisplayName("유효한 값으로 WinningLotto 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 6, 12, 33, 41, 45, 27, 29, 30})
    void createWinningLottoTest(int bonusNo) {
        // given
        List<LottoNumber> lottoNumbers = setUpLottoNumbers();
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNo);

        // when
        Lotto lotto = new Lotto(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusLottoNumber);

        // then
        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
        assertThat(winningLotto.getLotto()).isEqualTo(lotto);
        assertThat(winningLotto.getBonusNo()).isEqualTo(bonusLottoNumber);
    }

}