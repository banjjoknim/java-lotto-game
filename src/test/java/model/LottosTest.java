package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    private Lottos setLottos() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = Arrays.asList(2, 5, 7, 9, 10, 16);
        List<Integer> numbers3 = Arrays.asList(2, 5, 30, 9, 10, 16);
        List<Integer> numbers4 = Arrays.asList(2, 5, 30, 9, 7, 16);
        Lotto lotto = new Lotto(numbers);
        Lotto lotto2 = new Lotto(numbers2);
        Lotto lotto3 = new Lotto(numbers3);
        Lotto lotto4 = new Lotto(numbers4);
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(lotto);
        lottoList.add(lotto2);
        lottoList.add(lotto3);
        lottoList.add(lotto4);
        return new Lottos(lottoList);
    }

    @DisplayName("산출된 통계를 테스트한다.")
    @Test
    void getStatisticsTest() {
        Lottos lottos = setLottos();

        List<Integer> winningNumbers = Arrays.asList(2, 5, 7, 9, 10, 16);
        int bonusNo = 30;
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNo);

        Map<Rank, Long> statistics = lottos.getStatistics(winningLotto);

        assertThat(statistics.size()).isEqualTo(3);
        assertThat(statistics.getOrDefault(Rank.NONE, 0L)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Rank.FIRST, 0L)).isEqualTo(1);
        assertThat(statistics.getOrDefault(Rank.SECOND, 0L)).isEqualTo(2);
    }

    @DisplayName("수익률 계산을 테스트한다.")
    @Test
    void calculateYieldTest() {
        Lottos lottos = setLottos();

        List<Integer> winningNumbers = Arrays.asList(2, 5, 7, 9, 10, 16);
        int bonusNo = 30;
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNo);

        assertThat(lottos.calculateYield(winningLotto)).isEqualTo((Rank.FIRST.getWinningMoney() + Rank.SECOND.getWinningMoney() * 2) / (lottos.getLottos().size() * Lotto.PRICE));
    }

}