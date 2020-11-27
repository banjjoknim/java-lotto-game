package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("WinningLotto 객체의 초기화된 상태값을 테스트한다.")
    @Test
    void createWinningLottoTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        int bonusNo = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNo);
        assertThat(winningLotto.getLotto().getNumbers()).isEqualTo(numbers);
        assertThat(winningLotto.getBonusNo()).isEqualTo(bonusNo);
    }

    @DisplayName("WinningLotto와 userLotto의 번호 일치여부 검증메서드를 테스트한다.")
    @Test
    void matchTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> userNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        Lotto lotto = new Lotto(numbers);
        Lotto userLotto = new Lotto(userNumbers);
        int bonusNo = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNo);
        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("WinningLotto의 보너스 볼 검증 메서드를 테스트한다.")
    @Test
    void validateBonusNoTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNo = 6;
        assertThatThrownBy(() -> new WinningLotto(new Lotto(numbers), bonusNo))
                .isInstanceOf(IllegalArgumentException.class);
    }
}