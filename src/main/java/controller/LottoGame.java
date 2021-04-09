package controller;

import model.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        OutputView.printPleaseInputPurchaseMoney();
        Money money = InputView.inputMoney();
        Lottos lottos = Lottos.issueLottos(money);
        OutputView.printHasPurchased(lottos);
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();

        OutputView.printWinningStatistics();
        OutputView.printSeparationLine();
        OutputView.printStatistics(lottos, winningLotto);
    }

}
