package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class LottosTest {

    @DisplayName("WinningLotto 와 Lottos 를 대조하여 통계내는 기능을 테스트 한다.")
    @Test
    void getStatisticsTest() {
        // given
        List<LottoNumber> lottoNumbers1 = Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .map(LottoNumber::new)
                .collect(toList());
        List<LottoNumber> lottoNumbers2 = Arrays.asList(2, 5, 6, 7, 8, 11)
                .stream()
                .map(LottoNumber::new)
                .collect(toList());
        Lotto userLotto1 = new Lotto(lottoNumbers1);
        Lotto userLotto2 = new Lotto(lottoNumbers2);
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(userLotto1);
        lottoList.add(userLotto2);

        List<LottoNumber> winningLottoNumbers = Arrays.asList(2, 5, 6, 7, 8, 10)
                .stream()
                .map(LottoNumber::new)
                .collect(toList());
        LottoNumber bonusNumber = new LottoNumber(11);
        Lotto inputWinningLotto = new Lotto(winningLottoNumbers);
        WinningLotto winningLotto = new WinningLotto(inputWinningLotto, bonusNumber);


        Lottos lottos = new Lottos(lottoList);

        // when
        Map<Rank, Integer> statistics = lottos.getStatistics(winningLotto);

        // then
        assertThat(statistics.get(Rank.FIRST)).isEqualTo(0);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.get(Rank.THIRD)).isEqualTo(0);
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(1);
    }
}