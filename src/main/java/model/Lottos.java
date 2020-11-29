package model;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public double calculateYield(WinningLotto winningLotto) {
        Map<Rank, Long> statistics = getStatistics(winningLotto);
        long benefit = calculateBenefit(statistics);
        long payment = calculatePayment();
        return (double) benefit / payment;
    }

    private long calculateBenefit(Map<Rank, Long> statistics) {
        return statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getWinningMoney() * entry.getValue())
                .sum();
    }

    private long calculatePayment() {
        return lottos.size() * Lotto.PRICE;
    }

    public Map<Rank, Long> getStatistics(WinningLotto winningLotto) {
        return lottos.stream()
                .collect(groupingBy((lotto -> winningLotto.match(lotto)), counting()));
    }
}
