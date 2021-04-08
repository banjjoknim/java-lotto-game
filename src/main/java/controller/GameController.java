package controller;

import model.Lottos;
import model.WinningLotto;
import view.GameViewInput;
import view.GameViewOutput;

public class GameController {

    private static void start() {
        Lottos lottos = GameViewInput.purchaseLottos();

        WinningLotto winningLotto = GameViewInput.inputWinningNumbersAndBonusNo();

        GameViewOutput.printStatistics(winningLotto, lottos);
    }

    public static void main(String[] args) {
        GameController.start();
    }
}
