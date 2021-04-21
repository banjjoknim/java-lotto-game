package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class LottoStatisticsTest {

    private static WinningLotto winningLotto;
    private static Lottos lottos;

    @BeforeEach
    void setUp() {
        setUpLottos();
        setUpWinningLotto();
    }

    void setUpWinningLotto() {
        List<LottoNumber> winningLottoNumbers = Arrays.asList(2, 5, 6, 7, 8, 10)
                .stream()
                .map(LottoNumber::from)
                .collect(toList());
        LottoNumber bonusNumber = LottoNumber.from(11);
        Lotto inputWinningLotto = new Lotto(winningLottoNumbers);
        winningLotto = new WinningLotto(inputWinningLotto, bonusNumber);
    }

    void setUpLottos() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .map(LottoNumber::from)
                .collect(toList());
        List<LottoNumber> lottoNumbers2 = Arrays.asList(2, 5, 6, 7, 8, 11)
                .stream()
                .map(LottoNumber::from)
                .collect(toList());
        Lotto userLotto1 = new Lotto(lottoNumbers1);
        Lotto userLotto2 = new Lotto(lottoNumbers2);
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(userLotto1);
        lottoList.add(userLotto2);
        lottos = new Lottos(lottoList);
    }

    @DisplayName("LottoStatistics 의 통계에 대한 변경을 시도했을때 예외 처리를 테스트 한다.")
    @Test
    void statisticsNotModifiableTest() {
        // given
        LottoStatistics lottoStatistics = new LottoStatistics(lottos.produceStatistics(winningLotto));
        Map<Rank, Integer> statistics = lottoStatistics.getStatistics();

        // when

        // then
        assertThatThrownBy(() -> statistics.put(Rank.FIFTH, 1)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("LottoStatistics 의 수익률을 계산하는 기능을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1234, 5678, 10000})
    void calculateYield(int amount) {
        // given
        double benefit = Rank.SECOND.getWinningMoney().getAmount().add(Rank.FIFTH.getWinningMoney().getAmount()).doubleValue();
        Money money = new Money(new BigDecimal(amount));
        double expectedYield = new BigDecimal(benefit)
                .divide(money.calculateTotalSpendMoney(), 3, RoundingMode.HALF_EVEN)
                .doubleValue();
        LottoStatistics lottoStatistics = new LottoStatistics(lottos.produceStatistics(winningLotto));
        BigDecimal totalBenefit = lottoStatistics.calculateTotalBenefit();

        // when
        double yield = money.calculateYield(totalBenefit);

        // then
        assertThat(yield).isEqualTo(expectedYield);
    }
}