package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class LottoGame {

    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoneyAmount());
        Lottos lottos = Lottos.issueLottos(money, new AutoLottoGenerator());
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = inputWinningLotto();
        LottoStatistics lottoStatistics = new LottoStatistics(lottos.produceStatistics(winningLotto));
        double yield = lottoStatistics.calculateYield(money);
        OutputView.printStatistics(lottoStatistics.getStatistics(), yield);
    }

    private static WinningLotto inputWinningLotto() {
        Lotto lotto = new Lotto(convertNumbersToLottoNumbers(InputView.inputNumbers()));
        LottoNumber bonusNumber = convertNumberToLottoNumber(InputView.inputNumber());
        return new WinningLotto(lotto, bonusNumber);
    }

    private static List<LottoNumber> convertNumbersToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::getLottoNumberFromCache)
                .collect(toList());
    }

    private static LottoNumber convertNumberToLottoNumber(int number) {
        return new LottoNumber(number);
    }

}
