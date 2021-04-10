package controller;

import model.Lottos;
import model.Rank;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class LottoGame {

    public static void main(String[] args) {
        Lottos lottos = InputView.purchaseLottos();
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        Map<Rank, Integer> statistics = lottos.produceStatistics(winningLotto);
        double yield = lottos.calculateYield(winningLotto);

        OutputView.printStatistics(statistics, yield);
    }

}
