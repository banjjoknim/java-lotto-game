package controller;

import model.Lottos;
import model.WinningLotto;
import view.GameView;

public class GameController {

    private static void start() {
        Lottos lottos = GameView.purchaseLottos();

        WinningLotto winningLotto = GameView.inputWinningNumbersAndBonusNo();

        GameView.printStatistics(winningLotto, lottos);
    }

    public static void main(String[] args) {
        GameController.start();
    }
}
