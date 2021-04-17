package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;

public class LottoStatistics {
    private static final int YIELD_SCALE = 3;

    private Map<Rank, Integer> statistics;

    public LottoStatistics(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public double calculateYield(Money money) {
        BigDecimal totalSpendMoney = money.calculateTotalSpendMoney();
        BigDecimal totalBenefit = calculateTotalBenefit(statistics);
        return totalBenefit.divide(totalSpendMoney, YIELD_SCALE, RoundingMode.HALF_EVEN).doubleValue();
    }

    private BigDecimal calculateTotalBenefit(Map<Rank, Integer> statistics) {
        return Arrays.stream(Rank.values())
                .map(rank -> rank.calculateBenefit(statistics.get(rank)))
                .reduce(BigDecimal.ZERO, (previousBenefit, nextBenefit) -> previousBenefit.add(nextBenefit));
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }

}
