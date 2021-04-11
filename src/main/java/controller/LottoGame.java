package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class LottoGame {

    public static void main(String[] args) {
        Money money = InputView.inputMoney();
        Lottos lottos = Lottos.issueLottos(money);
        OutputView.printLottos(lottos);

        Lotto lotto = InputView.inputLotto();
        WinningLotto winningLotto = InputView.inputWinningLotto(lotto);
        Map<Rank, Integer> statistics = lottos.produceStatistics(winningLotto);
        double yield = lottos.calculateYield(winningLotto, money);

        OutputView.printStatistics(statistics, yield);
    }

}
