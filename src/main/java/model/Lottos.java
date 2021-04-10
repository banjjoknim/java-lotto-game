package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static model.Lotto.LOTTO_PRICE;

public class Lottos {
    private static final int INITIALIZED_STATISTICS_RANK_VALUE = 0;
    private static final int ONE = 1;
    private static final int YIELD_SCALE = 3;

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public static Lottos issueLottos(Money money) {
        int issueCount = money.calculateIssueCount();
        List<Lotto> lottos = generateLottos(issueCount);
        return new Lottos(lottos);
    }

    private static List<Lotto> generateLottos(int issueCount) {
        return Stream.generate(Lotto::generateLotto)
                .limit(issueCount)
                .collect(toList());
    }

    public Map<Rank, Integer> getStatistics(WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = Arrays.stream(Rank.values())
                .collect(toMap(rank -> rank, rank -> INITIALIZED_STATISTICS_RANK_VALUE));
        lottos.forEach(lotto -> produceStatistics(winningLotto, statistics, lotto));
        return statistics;
    }

    private void produceStatistics(WinningLotto winningLotto, Map<Rank, Integer> statistics, Lotto lotto) {
        Rank rank = winningLotto.match(lotto);
        statistics.put(rank, statistics.get(rank) + ONE);
    }

    public double calculateYield(WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = getStatistics(winningLotto);
        BigDecimal totalSpendMoney = new BigDecimal(lottos.size() * LOTTO_PRICE);
        BigDecimal totalBenefit = calculateTotalBenefit(statistics);
        return totalBenefit.divide(totalSpendMoney, YIELD_SCALE, RoundingMode.HALF_EVEN).doubleValue();
    }

    private BigDecimal calculateTotalBenefit(Map<Rank, Integer> statistics) {
        long sumOfBenefit = Arrays.stream(Rank.values())
                .mapToLong(rank -> statistics.get(rank) * rank.getWinningMoney())
                .sum();
        return new BigDecimal(sumOfBenefit);
    }

}
