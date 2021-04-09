package controller;

import model.Lottos;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        Lottos lottos = InputView.purchaseLottos();
        OutputView.printLottos(lottos);
        WinningLotto winningLotto = InputView.inputWinningLotto();
        OutputView.printStatistics(lottos, winningLotto);
    }

}
