package model;

import java.util.Map;

public class LottoStatistics {

    private Map<Rank, Integer> statistics;

    public LottoStatistics(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }
}
