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
import static org.junit.jupiter.api.Assertions.assertAll;

class LottosTest {
    private static final BigDecimal LOTTO_PRICE = new BigDecimal(1000);

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
                .map(LottoNumber::new)
                .collect(toList());
        LottoNumber bonusNumber = new LottoNumber(11);
        Lotto inputWinningLotto = new Lotto(winningLottoNumbers);
        winningLotto = new WinningLotto(inputWinningLotto, bonusNumber);
    }

    void setUpLottos() {
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
        lottos = new Lottos(lottoList);
    }

    @DisplayName("Lottos 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 0, 5000, 11111, 5000000})
    void issueLottos(int amount) {
        // given
        Money money = new Money(new BigDecimal(amount));
        int issueCount = money.getAmount().divide(LOTTO_PRICE).intValue();
        NumberGenerator strategy = new RandomNumberGenerator();

        // when
        Lottos lottos = Lottos.issueLottos(money, strategy);

        // then
        assertThat(lottos.getLottos()).hasSize(issueCount);
    }

    @DisplayName("WinningLotto 와 Lottos 를 대조하여 통계내는 기능을 테스트 한다.")
    @Test
    void getStatisticsTest() {
        // given

        // when
        Map<Rank, Integer> statistics = lottos.produceStatistics(winningLotto);

        // then
        assertAll(
                () -> assertThat(statistics.get(Rank.FIRST)).isEqualTo(0),
                () -> assertThat(statistics.get(Rank.SECOND)).isEqualTo(1),
                () -> assertThat(statistics.get(Rank.THIRD)).isEqualTo(0),
                () -> assertThat(statistics.get(Rank.FOURTH)).isEqualTo(0),
                () -> assertThat(statistics.get(Rank.FIFTH)).isEqualTo(1)
        );
    }

    @DisplayName("Lottos의 수익률을 계산하는 기능을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1234, 5678, 10000})
    void calculateYieldTest(int amount) {
        // given
        double benefit = Rank.SECOND.getWinningMoney().getAmount().add(Rank.FIFTH.getWinningMoney().getAmount()).doubleValue();
        Money money = new Money(new BigDecimal(amount));
        BigDecimal expectedYield = new BigDecimal(benefit).divide(money.calculateTotalSpendMoney(), 3, RoundingMode.HALF_EVEN);

        // when
        double actualYield = lottos.calculateYield(winningLotto, money);

        // then
        assertThat(actualYield).isEqualTo(expectedYield.doubleValue());
    }

}