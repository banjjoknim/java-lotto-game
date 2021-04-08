package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public BigDecimal calculateYield(WinningLotto winningLotto) {
        Map<Rank, Long> statistics = getStatistics(winningLotto);
        BigDecimal benefit = calculateBenefit(statistics);
        BigDecimal payment = calculatePayment();
        return benefit.divide(payment);
    }

    private BigDecimal calculateBenefit(Map<Rank, Long> statistics) {
        return new BigDecimal(statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getWinningMoney() * entry.getValue())
                .sum());
    }

    private BigDecimal calculatePayment() {
        return new BigDecimal(lottos.size() * Lotto.PRICE);
    }

    public Map<Rank, Long> getStatistics(WinningLotto winningLotto) {
        return lottos.stream()
                .collect(groupingBy(lotto -> winningLotto.match(lotto), counting()));
    }
}
