package view;

import model.Lotto;
import model.LottoNumber;
import model.Money;
import model.WinningLotto;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static Money inputMoney() {
        try {
            return new Money(inputMoneyAmount());
        } catch (IllegalArgumentException e) {
            return inputMoney();
        }
    }

    private static BigDecimal inputMoneyAmount() {
        OutputView.printPleaseInputPurchaseMoney();
        return new BigDecimal(scanner.next());
    }

    public static Lotto inputLotto() {
        try {
            return Lotto.generateLottoByInput(inputLastWeekWinningLottoNumber());
        } catch (IllegalArgumentException e) {
            return inputLotto();
        }
    }

    private static String inputLastWeekWinningLottoNumber() {
        OutputView.printPleaseInputLastWeekWinningLottoNumber();
        return scanner.next();
    }

    public static WinningLotto inputWinningLotto(Lotto lotto) {
        try {
            String bonusBallNumber = inputBonusNumber();
            return new WinningLotto(lotto, new LottoNumber(Integer.parseInt(bonusBallNumber)));
        } catch (IllegalArgumentException e) {
            return inputWinningLotto(lotto);
        }
    }

    private static String inputBonusNumber() {
        OutputView.printPleaseInputBonusBallNumber();
        return scanner.next();
    }

}
