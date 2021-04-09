package controller;

import model.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        OutputView.printPleaseInputPurchaseMoney();
        Money money = InputView.inputMoney();
        Lottos lottos = LottoIssuer.issueLottos(money);
        OutputView.printHasPurchased(lottos);
        OutputView.printLottos(lottos);

        Lotto lotto = setLottoByInput();
        WinningLotto winningLotto = setWinningLottoByInput(lotto);

        OutputView.printWinningStatistics();
        OutputView.printSeparationLine();
        OutputView.printStatistics(lottos, winningLotto);
    }

    private static WinningLotto setWinningLottoByInput(Lotto lotto) {
        try {
            OutputView.printPleaseInputBonusBallNumber();
            String bonusBallNumber = InputView.inputBonusBallNumber();
            return LottoIssuer.issueWinningLotto(lotto, new LottoNumber(Integer.valueOf(bonusBallNumber)));
        } catch (IllegalArgumentException e) {
            return setWinningLottoByInput(lotto);
        }
    }

    private static Lotto setLottoByInput() {
        try {
            OutputView.printPleaseInputLastWeekWinningLottoNumber();
            String lastWeekWinningLottoNumber = InputView.inputLastWeekWinningLottoNumber();
            return LottoIssuer.generateLottoByInput(lastWeekWinningLottoNumber);
        } catch (IllegalArgumentException e) {
            return setLottoByInput();
        }
    }
}
