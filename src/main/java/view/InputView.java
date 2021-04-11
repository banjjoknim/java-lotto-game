package view;

import model.Lotto;
import model.LottoNumber;
import model.Money;
import model.WinningLotto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String SEPARATOR = ",";

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
            List<LottoNumber> lottoNumbers = Arrays.stream(inputLastWeekWinningLottoNumber().split(SEPARATOR))
                    .map(Integer::valueOf)
                    .map(LottoNumber::new)
                    .collect(toList());
            return new Lotto(lottoNumbers);
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