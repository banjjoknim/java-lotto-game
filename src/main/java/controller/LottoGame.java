package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class LottoGame {

    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoneyAmount());
        Lottos lottos = Lottos.issueLottos(money, new AutoLottoGenerator());
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = inputWinningLotto();
        Map<Rank, Integer> statistics = lottos.produceStatistics(winningLotto);
        Money totalBenefitMoney = money.calculateTotalBenefitMoney(statistics);
        double yield = money.calculateYield(totalBenefitMoney);
        OutputView.printStatistics(statistics, yield);
    }

    private static WinningLotto inputWinningLotto() {
        Lotto lotto = new Lotto(convertNumbersToLottoNumbers(InputView.inputNumbers()));
        LottoNumber bonusNumber = convertNumberToLottoNumber(InputView.inputNumber());
        return new WinningLotto(lotto, bonusNumber);
    }

    private static List<LottoNumber> convertNumbersToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .collect(toList());
    }

    private static LottoNumber convertNumberToLottoNumber(int number) {
        return LottoNumber.from(number);
    }

}
