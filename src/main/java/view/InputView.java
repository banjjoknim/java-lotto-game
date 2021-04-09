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
        BigDecimal amount = new BigDecimal(scanner.next());
        return new Money(amount);
    }

    public static WinningLotto inputWinningLotto() {
        Lotto lotto = inputLotto();
        try {
            OutputView.printPleaseInputBonusBallNumber();
            String bonusBallNumber = InputView.inputBonusNumber();
            return WinningLotto.issueWinningLotto(lotto, new LottoNumber(Integer.valueOf(bonusBallNumber)));
        } catch (IllegalArgumentException e) {
            return inputWinningLotto();
        }
    }

    private static String inputBonusNumber() {
        return scanner.next();
    }

    public static Lotto inputLotto() {
        try {
            OutputView.printPleaseInputLastWeekWinningLottoNumber();
            String lastWeekWinningLottoNumber = InputView.inputLastWeekWinningLottoNumber();
            return Lotto.generateLottoByInput(lastWeekWinningLottoNumber);
        } catch (IllegalArgumentException e) {
            return inputLotto();
        }
    }

    private static String inputLastWeekWinningLottoNumber() {
        return scanner.next();
    }

}
