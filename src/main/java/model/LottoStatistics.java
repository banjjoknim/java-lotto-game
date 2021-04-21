package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class LottoStatistics {

    private Map<Rank, Integer> statistics;

    public LottoStatistics(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public BigDecimal calculateTotalBenefit() {
        return Arrays.stream(Rank.values())
                .map(rank -> rank.calculateBenefit(statistics.get(rank)))
                .reduce(BigDecimal.ZERO, (previousBenefit, nextBenefit) -> previousBenefit.add(nextBenefit));
    }

    public Map<Rank, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }

}
