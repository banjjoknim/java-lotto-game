package view;

import model.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GameViewInput {
    private static Scanner sc = new Scanner(System.in);

    public static Lottos purchaseLottos() {
        GameViewOutput.printPleaseInputMoney();
        Money money = inputMoney();
        List<Lotto> lottos = getLottosAsMuchPaid(money.getMoney());
        GameViewOutput.printPurchased(lottos);
        printLottos(lottos);
        return new Lottos(lottos);
    }

    private static List<Lotto> getLottosAsMuchPaid(int money) {
        try {
            List<Lotto> lottos = Stream.generate(GameViewInput::purchaseLotto)
                    .limit(money / Lotto.PRICE)
                    .collect(toList());
            return lottos;
        } catch (IllegalArgumentException e) {
            return getLottosAsMuchPaid(money);
        }
    }

    private static Lotto purchaseLotto() {
        return new Lotto(LottoNumbers.createLottoNumbers());
    }

    private static Money inputMoney() {
        try {
            String money = sc.nextLine();
            return new Money(Integer.valueOf(money));
        } catch (IllegalArgumentException e) {
            sc = new Scanner(System.in);
            GameViewOutput.printPleaseInputAgain();
            return inputMoney();
        }
    }

    private static void printLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    public static WinningLotto inputWinningNumbersAndBonusNo() {
        GameViewOutput.printPleaseInputWinningNumbers();
        List<Integer> winningNumbers = inputWinningNumbers();
        GameViewOutput.printPleaseInputBonusNo();
        int bonusNo = inputBonusNo(winningNumbers);
        return new WinningLotto(new Lotto(winningNumbers), bonusNo);
    }

    private static List<Integer> inputWinningNumbers() {
        try {
            String input = sc.nextLine();
            List<Integer> winningNumbers = getWinningNumbersByInput(input);
            validateWinningNumbers(winningNumbers);
            return winningNumbers;
        } catch (IllegalArgumentException e) {
            GameViewOutput.printPleaseInputAgain();
            return inputWinningNumbers();
        }
    }

    private static void validateWinningNumbers(List<Integer> winningNumbers) {
        LottoNumbers.validateLottoNumbers(winningNumbers);
        winningNumbers.stream()
                .forEach(LottoNumber::validateNumber);
    }

    private static List<Integer> getWinningNumbersByInput(String input) {
        List<Integer> winningNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
        return winningNumbers;
    }

    private static int inputBonusNo(List<Integer> winningNumbers) {
        try {
            int bonusNo = sc.nextInt();
            validateInputBonusNo(winningNumbers, bonusNo);
            return bonusNo;
        } catch (InputMismatchException | IllegalArgumentException e) {
            sc = new Scanner(System.in);
            GameViewOutput.printPleaseInputAgain();
            return inputBonusNo(winningNumbers);
        }
    }

    private static void validateInputBonusNo(List<Integer> winningNumbers, int bonusNo) {
        LottoNumber.validateNumber(bonusNo);
        if (winningNumbers.contains(bonusNo)) {
            throw new IllegalArgumentException(WinningLotto.BONUS_NUMBER_IS_DUPLICATE);
        }
    }

}
