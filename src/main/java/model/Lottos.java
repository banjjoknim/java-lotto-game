package model;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Lottos {
    private static final int INITIALIZED_STATISTICS_RANK_VALUE = 0;
    private static final int ONE = 1;

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
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
}
