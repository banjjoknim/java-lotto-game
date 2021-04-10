package controller;

import model.Lottos;
import model.Money;
import model.Rank;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class LottoGame {

    public static void main(String[] args) {
        Money money = InputView.inputMoney();
        Lottos lottos = InputView.purchaseLottos(money);
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        Map<Rank, Integer> statistics = lottos.produceStatistics(winningLotto);
        double yield = lottos.calculateYield(winningLotto, money);

        OutputView.printStatistics(statistics, yield);
    }

}
